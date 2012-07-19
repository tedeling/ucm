package nl.tecon.ucm.dataimport.syslog
package parser

import collection.mutable.{Map => MutableMap}
import org.apache.log4j.Logger
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics
import nl.tecon.ucm.domain._
import nl.tecon.ucm.dataimport.util.DateFormatter
import nl.tecon.ucm.domain.CdrVsa
import scala.Some

class CdrVsaBuilder(val rawCdr: String) extends BuilderMap {
  private val LOG: Logger = Logger.getLogger(classOf[CdrBuilder])

  val InvalidConnectionId = "0000"

  def build()(implicit stats: SysLogParsingStatistics): Option[CdrVsa] = {
    if (!cdr.contains("connectionid")) {
      stats.addError()
      LOG.error("Failed to parse CDR VSA, no connectionId found %s".format(rawCdr))
      None
    } else if (cdr("connectionid") == InvalidConnectionId) {
      stats.addWarning()
      LOG.warn("Discarding connectionId: 0000. CDR: %s".format(rawCdr))
      None
    } else {
      val someCdrVsa = Some(CdrVsa(name = FeatureName.withName(cdr("fn")),
        featureTime = DateFormatter.parseDateAndTimeNoTimeZone(cdr("ft")),
        legId = cdr.getOrElse("legID", ""),
        forwardingReason = ForwardingReason.withName(cdr("frson")),
        status = FeatureStatus.withName(cdr("frs")),
        featureId = cdr("fid").toLong,
        connectionId = cdr("fcid"),
        forwardedNumber = cdr("fwdee"),
        forwardSourceNumber = cdr("fwder"),
        forwardToNumber = cdr("fwdto"),
        forwardFromNumber = cdr("frm"),
        calledNumber = cdr("cdn"),
        callingNumber = cdr("cgn"),
        originalRecord = rawCdr))
      stats.addSuccess()
      someCdrVsa
    }
  }
}
