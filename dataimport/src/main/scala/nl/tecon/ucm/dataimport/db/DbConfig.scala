package nl.tecon.ucm.dataimport.db

import org.mybatis.scala.config._
import nl.tecon.ucm.dataimport.syslog.dao.{CdrVsaDao, CdrDao, SysLogDao}
import org.apache.ibatis.`type`.{JdbcType, TypeHandlerRegistry}
import nl.tecon.ucm.domain.mybatis.{OptForwardingReasonTypeHandler, FeatureNameTypeHandler}
import nl.tecon.ucm.domain.ForwardingReason

object DbConfig {
  // Load datasource configuration
  val config = Configuration("mybatis-config.xml")

//  Create a configuration space, add the data access method
  config.addSpace("item") { space =>
    space += SysLogDao.findAfterId
    space += CdrDao.persist
    space += CdrDao.findByOriginalRecord
    space += CdrVsaDao.persist
    space += CdrVsaDao.findByOriginalRecord
  }

  val registry = config.configuration.getTypeHandlerRegistry



  // Build the session manager
  val persistenceContext = config.createPersistenceContext

}
