package nl.tecon.ucm.dataimport.syslog

import dao.SysLogDao
import org.springframework.stereotype.Service
import nl.tecon.ucm.dataimport.db.DbConfig
import parser.CdrParser

trait SysLogImport {
  def parseSysLog()
}

@Service
class SysLogImportImpl extends SysLogImport {
  def parseSysLog() {
    val db = DbConfig.persistenceContext

    db.transaction {
      implicit session =>
        SysLogDao.findAfterId(0) map (CdrParser.parse(_))
    }
  }
}

