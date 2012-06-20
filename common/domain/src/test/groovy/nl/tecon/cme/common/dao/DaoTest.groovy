package nl.tecon.cme.common.dao

import org.junit.Test
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertEquals

class DaoTest extends AbstractDaoTest {
    @Test
    public void shouldInitDatabase() {
        executeQuery("INSERT INTO HUNTGROUP VALUES(1, 1)")

        sql.eachRow('select * from HUNTGROUP') { row ->
            assertEquals(1, "${row.PILOT_NUMBER}".toInteger())
        }
    }

    @Test
    public void shouldBeAbleToRunTwice() {
        executeQuery("INSERT INTO HUNTGROUP VALUES(1, 1)")

        sql.eachRow('select * from HUNTGROUP') { row ->
            assertEquals(1, "${row.PILOT_NUMBER}".toInteger())
        }
    }
}
