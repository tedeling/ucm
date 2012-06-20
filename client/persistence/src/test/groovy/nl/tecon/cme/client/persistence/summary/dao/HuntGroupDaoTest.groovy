package nl.tecon.cme.client.persistence.summary.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.summary.HuntGroup
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class HuntGroupDaoTest extends AbstractDaoTest
{
  @Autowired
  HuntGroupDao huntGroupDao

  @Test
  void shouldInsert()
  {
    def group = new HuntGroup(huntGroupId: 1)
    huntGroupDao.persistHuntGroup(group)

    def found = sql.firstRow("SELECT * FROM HUNTGROUP WHERE HUNTGROUP_ID = 1")
    assert found.HUNTGROUP_ID == 1
  }

  @Test
  void shouldFindOnGroupId()
  {
    sql.execute "INSERT INTO HUNTGROUP (HUNTGROUP_ID) VALUES(1)"

    def count = huntGroupDao.findDuplicateCount(1)

    assert count == 1
  }
}
