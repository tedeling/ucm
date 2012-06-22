package nl.tecon.cme.client.syslog.impl

import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FlatSpec}
import nl.tecon.cme.client.persistence.syslog.dao.SysLogDao
import nl.tecon.cme.client.syslog.parser.CdrParser
import org.mockito.{MockitoAnnotations, Mock}
import org.mockito.Mockito._
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class SysLogImportImplTest extends FlatSpec with MockitoSugar with BeforeAndAfter {

  var subject: SysLogImportImpl = _
  @Mock
  var sysLogDao: SysLogDao = _

  @Mock
  var cdrParser: CdrParser = _

  before {
    MockitoAnnotations.initMocks(this)
    subject = new SysLogImportImpl(sysLogDao, cdrParser)
  }

  behavior of "SysLogImport"

  it should "import" in {

    when(sysLogDao.findSyslogEntities(0l)).thenReturn(List())

  }

}