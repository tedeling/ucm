package nl.tecon.cme.client.boot

import nl.tecon.cme.client.acd.AcdImport
import nl.tecon.cme.client.syslog.SysLogImport
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.verify

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 12:21 PM
 */
class DataImportTest
{
  @Mock
  SysLogImport sysLogImport

  @Mock
  AcdImport acdImport

  @Before
  void setUp()
  {
    MockitoAnnotations.initMocks this
  }

  @Test
  void shouldStart()
  {
    def dataImport = new DataImport()
    dataImport.sysLogImport = sysLogImport
    dataImport.acdImport = acdImport
    dataImport.start()

    verify(sysLogImport).parseSysLog()
    verify(acdImport).parseAcdFiles()
  }
}
