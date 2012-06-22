package nl.tecon.cme.client.syslog.parser

import nl.tecon.cme.client.AbstractSpringMockContextTest
import nl.tecon.cme.client.persistence.cdr.dao.CdrDao
import nl.tecon.cme.common.domain.cdr.Cdr
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/15/10 - 11:58 PM
 */
class CdrParserImplTest extends AbstractSpringMockContextTest
{
  @Mock
  CdrDao cdrDao

  @Before
  void setUp()
  {
    when(context.getBean(CdrDao.class)).thenReturn cdrDao
  }

  @Test
  public void shouldParse()
  {
    ArgumentCaptor<Cdr> argument = ArgumentCaptor.forClass(Cdr.class)

    String cdr = " CME-MRI: Dec 16 10:15:50.894: %VOIPAAA-5-VOIP_CALL_HISTORY: CallLegType 2, ConnectionId CA5210B082B11E0A142A279BD84410, SetupTime 10:14:59.214 CET Thu Dec 16 2010, PeerAddress 510, PeerSubAddress , DisconnectCause 10  , DisconnectText normal call clearing (16), ConnectTime 10:14:59.294 CET Thu Dec 16 2010, DisconnectTime 10:15:50.894 CET Thu Dec 16 2010, CallOrigin 1, ChargedUnits 0, InfoType 2, TransmitPackets 2562, TransmitBytes 409920, ReceivePackets 2576, ReceiveBytes 411524";

    def parser = new CdrParserImpl()
    parser.parseCdr(cdr)

    verify(cdrDao).persistCdr(argument.capture());

    assert argument.value.callLegType == 2
  }
}
