package nl.tecon.cme.client.persistence.syslog.dao

import nl.tecon.cme.client.persistence.syslog.dao.SysLogDao
import nl.tecon.cme.common.dao.AbstractDaoTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.junit.Ignore

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/15/10 - 11:09 PM
 */

@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class SysLogDaoMyBatisImplTest extends AbstractDaoTest
{
  @Autowired
  SysLogDao sysLogDao

  @Test
  void findLogsAfterId()
  {
    def map = [priority: 5, message: 'Hello', facility: 5]

    sql.execute "INSERT INTO SystemEvents (Priority, Message, Facility) VALUES($map.priority, $map.message, $map.facility)"
    def values = sysLogDao.findSyslogEntities(0)

    assert values.apply(0).message == "Hello"
    assert values.apply(0).priority == 5
  }
}
