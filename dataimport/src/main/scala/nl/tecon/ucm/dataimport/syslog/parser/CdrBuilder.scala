package nl.tecon.ucm.dataimport.syslog.parser

import collection.mutable.{Map => MutableMap}
import nl.tecon.ucm.domain.Cdr
import nl.tecon.ucm.dataimport.util.DateFormatter
import org.apache.log4j.Logger
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics

class CdrBuilder(val rawCdr: String) {
  private val LOG: Logger = Logger.getLogger(classOf[CdrBuilder])

  val InvalidConnectionId = "0000"

  val cdr = MutableMap[String, String]()

  def parse(keyValue: String) {
    val trimmed = keyValue.trim()
    val seperatorLocation = trimmed.indexOf(' ')

    if (seperatorLocation >= 0) {
      val value = trimmed.substring(seperatorLocation + 1).trim()
      val key = trimmed.substring(0, seperatorLocation).trim()

      if (!value.isEmpty) {
        cdr(key.toLowerCase) = value
      }
    }
  }

  def build()(implicit stats: SysLogParsingStatistics): Option[Cdr] = {
    if (!cdr.contains("connectionid")) {
      stats.addError()
      LOG.error("Failed to parse CDR, no connectionId found %s".format(rawCdr))
      None
    } else if (cdr("connectionid") == InvalidConnectionId) {
      stats.addWarning()
      LOG.warn("Discarding connectionId: 0000. CDR: %s".format(rawCdr))
      None
    } else {
      val someCdr = Some(Cdr(connectionId = cdr("connectionid"),
        callLegType = cdr("calllegtype").toInt,
        setUpTime = DateFormatter.parseDateAndTime(cdr("setuptime")),
        peerAddress = cdr.getOrElse("peeraddress", ""),
        peerSubAddress = cdr.getOrElse("peersubaddress", ""),
        disconnectCause = cdr("disconnectcause"),
        disconnectText = cdr("disconnecttext"),
        connectTime = DateFormatter.parseDateAndTime(cdr("connecttime")),
        disconnectTime = DateFormatter.parseDateAndTime(cdr("disconnecttime")),
        callOrigin = cdr.getOrElse("callOrigin", ""),
        chargedUnits = cdr.getOrElse("chargedUnits", ""),
        infoType = cdr.getOrElse("infoType", ""),
        transmitPackets = cdr.getOrElse("transmitPackets", "0").toLong,
        transmitBytes = cdr.getOrElse("transmitBytes", "0").toLong,
        receivedPackets = cdr.getOrElse("receivedPackets", "0").toLong,
        receivedBytes = cdr.getOrElse("receivedBytes", "0").toLong,
        originalRecord = rawCdr))
      stats.addSuccess()
      someCdr
    }
  }
}
