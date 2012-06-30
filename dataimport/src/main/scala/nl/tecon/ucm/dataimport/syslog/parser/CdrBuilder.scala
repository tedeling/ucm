package nl.tecon.ucm.dataimport.syslog.parser

import collection.mutable.{Map => MutableMap}
import nl.tecon.ucm.domain.Cdr
import nl.tecon.ucm.dataimport.util.DateFormatter

class CdrBuilder {
  val InvalidConnectionId = "0000"

  val cdr = MutableMap[String, String]()

  def parse(keyValue: String) {
    keyValue.trim

    val seperatorLocation = keyValue.indexOf(' ')

    if (seperatorLocation >= 0) {
      val value = keyValue.substring(seperatorLocation + 1).trim()
      val key = keyValue.substring(0, seperatorLocation).trim()

      if (!value.isEmpty) {
        cdr(key.toLowerCase) = value
      }
    }
  }

  def build(): Cdr = {
    try {
      Cdr(connectionId = cdr("connectionid"),
        callLegType = cdr("calllegtype").toInt,
        setUpTime = DateFormatter.parseDateAndTime(cdr("setuptime")),
        peerAddress = cdr("peeraddress"),
        peerSubAddress = cdr("peersubaddress"),
        disconnectCause = cdr("disconnectcause"),
        disconnectText = cdr("disconnecttext"),
        connectTime = DateFormatter.parseDateAndTime(cdr("connecttime")),
        disconnectTime = DateFormatter.parseDateAndTime(cdr("disconnecttime")),
        callOrigin = cdr("callOrigin"),
        chargedUnits = cdr("chargedUnits"),
        infoType = cdr("infoType"),
        transmitPackets = cdr("transmitPackets").toLong,
        transmitBytes = cdr("transmitBytes").toLong,
        receivedPackets = cdr("receivedPackets").toLong,
        receivedBytes = cdr("receivedBytes").toLong,
        originalRecord = cdr("originalRecord"))
    } catch
  }
}
