package nl.tecon.ucm.dataimport.syslog.parser

import org.specs2.mutable.{SpecificationWithJUnit, Specification}
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics
import nl.tecon.ucm.domain.Cdr
import org.joda.time.{DateTimeZone, DateTime}
import org.joda.time.tz.DateTimeZoneBuilder
import org.junit.runner.RunWith
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CdrBuilderSpec extends Specification {
  "The CdrBuilder" should {

    "parse a raw CDR properly" in {
      implicit val stats = new SysLogParsingStatistics

      val cdr = "original"
      val builder = new CdrBuilder(cdr)

      builder.parse("ConnectionId CA5210B082B11E0A142A279BD84410")
      builder.parse(" CallLegType 2")
      builder.parse(" SetupTime 1:10:30.214 CET Sun Jul 8 2012")
      builder.parse(" PeerAddress 510")
      builder.parse(" PeerSubAddress ")
      builder.parse("  DisconnectCause 10   ")
      builder.parse("  DisconnectText normal call clearing (16)  ")
      builder.parse("ConnectTime 1:10:31.214 CET Sun Jul 8 2012")
      builder.parse("DisconnectTime 1:10:32.214 CET Sun Jul 8 2012")
      builder.parse("CallOrigin 1")
      builder.parse("ChargedUnits 3")
      builder.parse("InfoType 2")
      builder.parse("TransmitPackets 2562")
      builder.parse("TransmitBytes 409920")
      builder.parse("ReceivePackets 2576")
      builder.parse("ReceiveBytes 411524")

      builder.build() === Some(Cdr(connectionId = "CA5210B082B11E0A142A279BD84410",
                                        callLegType = 2,
                                        setUpTime = new DateTime(2012, 7, 8, 1, 10, 30, 214, DateTimeZone.forID("CET")),
                                        peerAddress = "510",
                                        peerSubAddress = "",
                                        disconnectCause = "10",
                                        disconnectText = "normal call clearing (16)",
                                        connectTime = new DateTime(2012, 7, 8, 1, 10, 31, 214, DateTimeZone.forID("CET")),
                                        disconnectTime = new DateTime(2012, 7, 8, 1, 10, 32, 214, DateTimeZone.forID("CET")),
                                        callOrigin = "1",
                                        chargedUnits = "3",
                                        infoType = "2",
                                        transmitPackets = 2562,
                                        transmitBytes = 409920,
                                        receivedPackets = 2576,
                                        receivedBytes = 411524,
                                        originalRecord = cdr))
    }
  }
}
