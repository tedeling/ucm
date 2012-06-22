package nl.tecon.cme.client.acd.impl

import nl.tecon.cme.client.acd.AcdImport
import nl.tecon.cme.client.acd.persist.AcdBlockPersist
import nl.tecon.cme.client.common.ImportResultHelper
import nl.tecon.cme.client.common.ParseContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 12:02 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:context-import-scanner-test.xml", "classpath:context-acd-config-test.xml"])
class AcdImportImplIntegrationTest
{
  @Autowired
  AcdImport acdImport

  @Autowired
  AcdBlockPersist blockPersister;

  @Before
  void setUp()
  {
    ImportResultHelper.reset()
  }

  @Test
  void shouldInitializeProperly()
  {
    assert "src/test/resources/acd/fullset" == ((AcdImportImpl) acdImport).acdDirectory
  }

  @Test
  void shouldParse()
  {
    def blockCount = acdImport.parseAcdFiles()

    def result = ParseContext.importResult

    assert 0 == result.importFailureCount
    assert 75 == blockCount

    def blocks = ((AcdBlockPersistMock) blockPersister).blocks
    assert 75 == blocks.size
  }
}
