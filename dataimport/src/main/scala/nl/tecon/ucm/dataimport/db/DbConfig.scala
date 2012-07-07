package nl.tecon.ucm.dataimport.db

import org.mybatis.scala.config._
import nl.tecon.ucm.dataimport.syslog.dao.{CdrDao, SysLogDao}

object DbConfig {
  // Load datasource configuration
  val config = Configuration("mybatis-config.xml")

//  Create a configuration space, add the data access method
  config.addSpace("item") { space =>
    space += SysLogDao.findAfterId
    space += CdrDao.persist
    space += CdrDao.findByOriginalRecord
  }

  // Build the session manager
  val persistenceContext = config.createPersistenceContext

}
