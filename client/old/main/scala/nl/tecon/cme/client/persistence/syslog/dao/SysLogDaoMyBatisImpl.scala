package nl.tecon.cme.client.persistence.syslog.dao

import org.mybatis.spring.support.SqlSessionDaoSupport
import org.springframework.stereotype.Repository
import scalaj.collection.Imports._
import nl.tecon.ucm.domain.SysLog

@Repository
class SysLogDaoMyBatisImpl extends SqlSessionDaoSupport with SysLogDao {

  def findSyslogEntities(afterId: Long) = {

    val result = getSqlSession.selectList("SysLogDao.findSyslogEntities", afterId)

    result.asInstanceOf[Seq[SysLog]]
  }
}