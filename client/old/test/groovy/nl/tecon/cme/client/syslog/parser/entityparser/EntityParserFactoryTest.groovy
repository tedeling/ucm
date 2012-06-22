package nl.tecon.cme.client.syslog.parser.entityparser

import nl.tecon.cme.client.syslog.RawCdr
import org.junit.Test
import static junit.framework.Assert.assertNull

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/21/10 - 1:07 AM
 */
class EntityParserFactoryTest
{
  @Test
  void shouldGetHistoryEntityParser()
  {
    def cdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")
    def parser = EntityParserFactory.createEntityParser(cdr)

    assert parser.class == CdrHistoryEntityParser.class
  }

  @Test
  void shouldGetVsaEntityParser()
  {
    def cdr = new RawCdr(" CME-MRI: Dec 16 10:15:50.930: %VOIPAAA-5-VOIP_FEAT_HISTORY: FEAT_VSA=fn:CFA,ft:12/16/2010 10:14:59.206,frs:0,fid:113897,fcid:CA5210B082B11E0A146A279BD84410,legID:F3AC,frson:1,fdcnt:1,fwder:880888888,fwdee:00104193158,fwdto:510,frm:880888888,bguid:CA4E6727082B11E09D8300164610A2D8")
    def parser = EntityParserFactory.createEntityParser(cdr)

    assert parser.class == CdrVsaEntityParser.class
  }

  @Test
  void shouldNotGetAnyParser()
  {
    def cdr = new RawCdr("INVALID")
    def parser = EntityParserFactory.createEntityParser(cdr)

    assertNull parser
  }
}
