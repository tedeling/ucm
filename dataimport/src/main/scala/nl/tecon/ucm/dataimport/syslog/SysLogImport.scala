package nl.tecon.ucm.dataimport.syslog

import dao.SysLogDao
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import nl.tecon.ucm.dataimport.db.DbConfig

trait SysLogImport {
  def parseSysLog()
}

@Service
class SysLogImportImpl extends SysLogImport {
  def parseSysLog() {
    val db = DbConfig.persistenceContext

    db.transaction { implicit session =>
      SysLogDao.findAfterId(150616) map (x => println(x.id))
    }
  }
}

