package nl.tecon.cme.client.syslog.impl

import org.springframework.beans.factory.annotation.Autowired
import nl.tecon.cme.client.persistence.syslog.dao.SysLogDao
import org.springframework.stereotype.Service
import nl.tecon.cme.client.syslog.parser.CdrParser
import nl.tecon.cme.client.syslog.SysLogImport


@Service
class SysLogImportImpl @Autowired() (sysLogDao: SysLogDao, cdrParser: CdrParser) extends SysLogImport {
  def parseSysLog() {
    import scala.collection.JavaConversions._
    for (log <- sysLogDao.findSyslogEntities(0l)) {
      cdrParser.parseCdr(log.getMessage)
    }
  }

}
