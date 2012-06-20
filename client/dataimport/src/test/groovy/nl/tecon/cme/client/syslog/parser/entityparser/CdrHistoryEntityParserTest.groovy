package nl.tecon.cme.client.syslog.parser.entityparser

import nl.tecon.cme.client.AbstractSpringMockContextTest
import nl.tecon.cme.client.common.ParseContext
import nl.tecon.cme.client.persistence.cdr.dao.CdrDao
import nl.tecon.cme.client.syslog.RawCdr
import nl.tecon.cme.common.domain.cdr.Cdr
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import static org.mockito.Mockito.*

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/21/10 - 12:51 AM
 */
class CdrHistoryEntityParserTest extends AbstractSpringMockContextTest
{
  @Mock
  CdrDao cdrDao

  CdrHistoryEntityParser parser

  @Before
  void setUp()
  {
    when(context.getBean(CdrDao.class)).thenReturn cdrDao

    parser = new CdrHistoryEntityParser()

    ParseContext.importResult.reset()
  }

  @Test
  void shouldParseRecord()
  {
    ArgumentCaptor<Cdr> argument = ArgumentCaptor.forClass(Cdr.class)

    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524")

    parser.parseRecord(rawCdr)

    verify(cdrDao).persistCdr(argument.capture());

    def cdr = argument.value

    assert cdr.callLegType == 2
    assert cdr.disconnectCause == "10"
    assert cdr.connectionId == "CA5210B082B11E0A142A279BD84410"
  }

  @Test
  void shouldFailParseRecord()
  {
    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType INVALID")

    parser.parseRecord(rawCdr)

    verifyZeroInteractions(cdrDao)

    assert ParseContext.importResult.importFailureCount == 1
  }

  @Test
  void shouldWarnParseRecord()
  {
    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_CALL_HISTORY: ConnectionId 0000")

    parser.parseRecord(rawCdr)

    verifyZeroInteractions(cdrDao)

    assert ParseContext.importResult.importWarningCount == 1
  }
}
