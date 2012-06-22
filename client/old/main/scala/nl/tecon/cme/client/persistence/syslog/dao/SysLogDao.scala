package nl.tecon.cme.client.persistence.syslog.dao

import nl.tecon.ucm.domain.SysLog

trait SysLogDao {
  def findSyslogEntities(afterId: Long): Seq[SysLog]
}