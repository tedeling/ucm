package nl.tecon.cme.common.dao

import groovy.sql.Sql
import javax.sql.DataSource
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import org.junit.After
import org.springframework.test.context.transaction.TransactionConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:/context-dbconfig-test.xml"])
@Transactional
@TransactionConfiguration(defaultRollback = true)
abstract class AbstractDaoTest {
    @Autowired
    @Qualifier("cmeDataSource")
    DataSource dataSource

    Sql sql

    @Before
    public final void initSql() {
        sql = new Sql(dataSource)
    }

    @After
    public void closeSql() {

        sql.close()
    }

    protected def executeQuery(String query) {
        sql.execute(query)
    }
}
