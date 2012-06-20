package nl.tecon.cme.client.persistence.syslog.dao

import nl.tecon.cme.common.domain.syslog.SysLog


trait SysLogDao {
  def findSyslogEntities(afterId: Long): Seq[SysLog]
}