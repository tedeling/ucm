package nl.tecon.ucm.dataimport.syslog
package parser

import collection.mutable.{Map => MutableMap}
import org.apache.log4j.Logger
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics
import nl.tecon.ucm.domain._
import nl.tecon.ucm.dataimport.util.DateFormatter
import nl.tecon.ucm.domain.CdrVsa
import scala.Some

class CdrVsaBuilder(val rawCdr: String) extends BuilderMap(separator = ":") {
  private val LOG: Logger = Logger.getLogger(classOf[CdrBuilder])

  val InvalidConnectionId = "0000"

  def build()(implicit stats: SysLogParsingStatistics): Option[CdrVsa] = {
    if (!cdr.contains("fcid")) {
      stats.addError()
      LOG.error("Failed to parse CDR VSA, no connectionId found in %s".format(rawCdr))
      None
    } else if (cdr("fcid") == InvalidConnectionId) {
      stats.addWarning()
      LOG.warn("Discarding connectionId: 0000. CDR: %s".format(rawCdr))
      None
    } else {
      val someCdrVsa = Some(CdrVsa(name = FeatureName.withName(cdr("fn")),
        featureTime = DateFormatter.parseDateAndTimeNoTimeZone(cdr("ft")),
        legId = cdr.getOrElse("legid", ""),
        forwardingReason = cdr.get("frson") match {
          case Some(n) => Some(ForwardingReason.withName(n))
          case None => None
        },
        status = FeatureStatus.withName(cdr("frs")),
        featureId = cdr("fid").toLong,
        connectionId = cdr("fcid"),
        forwardedNumber = cdr.getOrElse("fwdee", ""),
        forwardSourceNumber = cdr.getOrElse("fwder", ""),
        forwardToNumber = cdr.getOrElse("fwdto", ""),
        forwardFromNumber = cdr.getOrElse("frm", ""),
        calledNumber = cdr("cdn"),
        callingNumber = cdr("cgn"),
        originalRecord = rawCdr))
      stats.addSuccess()
      someCdrVsa
    }
  }
}
