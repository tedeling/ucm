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
      val rawName: String = cdr("fn")

      if (FeatureName.exists(rawName)) {
        val someCdrVsa = Some(CdrVsa(name = FeatureName.withName(rawName),
          featureTime = DateFormatter.parseDateAndTimeNoTimeZone(cdr("ft")),
          legId = cdr.getOrElse("legid", ""),
          forwardingReason = cdr.get("frson") match {
            case Some(n) => ForwardingReason.forRawValue(n)
            case None => ForwardingReason.UNDEFINED
          },
          status = cdr.get("frs") match {
            case Some("0") => FeatureStatus.SUCCESS
            case Some("1") => FeatureStatus.FAIL
            case None => {
              stats.addWarning()
              LOG.warn("No feature status found, assuming fail")
              FeatureStatus.FAIL
            }
          },
          featureId = cdr("fid").toLong,
          connectionId = cdr("fcid"),
          forwardedNumber = cdr.getOrElse("fwdee", ""),
          forwardSourceNumber = cdr.getOrElse("fwder", ""),
          forwardToNumber = cdr.getOrElse("fwdto", ""),
          forwardFromNumber = cdr.getOrElse("frm", ""),
          calledNumber = cdr.getOrElse("cdn", ""),
          callingNumber = cdr.getOrElse("cgn", ""),
          originalRecord = rawCdr))
        stats.addSuccess()
        someCdrVsa
      } else {
        stats.addWarning()
        LOG.warn("Unknown VSA name '%s'".format(rawName))
        None
      }
    }
  }

}
