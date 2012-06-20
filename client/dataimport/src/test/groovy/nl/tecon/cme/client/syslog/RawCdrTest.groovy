package nl.tecon.cme.client.syslog

import org.junit.Test

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/22/10 11:59 AM
 */
class RawCdrTest
{
  @Test
  void shouldGetType()
  {
    def cdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")
    assert cdr.type == "%VOIPAAA-5-VOIP_CALL_HISTORY"
  }

  @Test
  void shouldGetEnd()
  {
    def cdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")
    assert cdr.typeEnd == 58
  }

  @Test
  void shouldSplit()
  {
    def cdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")
    assert cdr.splittedWithoutType[0] == "CallLegType 2"
  }

}
