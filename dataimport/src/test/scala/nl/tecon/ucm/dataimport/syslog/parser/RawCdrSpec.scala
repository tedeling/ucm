package nl.tecon.ucm.dataimport.syslog.parser

import org.specs2.mutable.SpecificationWithJUnit
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

class RawCdrSpec extends SpecificationWithJUnit {
  "The RawCDR" should {
    val cdr = RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")

    "get a type" in {
      cdr.cdrType() must beSome("%VOIPAAA-5-VOIP_CALL_HISTORY")
    }

    "split on type" in {
      cdr.splitWithoutType()(0) must equalTo("CallLegType 2")
    }
  }
}
