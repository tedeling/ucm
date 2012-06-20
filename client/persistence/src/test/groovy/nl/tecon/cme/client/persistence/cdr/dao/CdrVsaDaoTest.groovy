package nl.tecon.cme.client.persistence.cdr.dao

import java.sql.Timestamp
import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.cdr.CdrVsa
import nl.tecon.cme.common.domain.cdr.FeatureName
import nl.tecon.cme.common.domain.cdr.FeatureStatus
import nl.tecon.cme.common.domain.cdr.ForwardingReason
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.test.context.ContextConfiguration

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/22/10 - 10:09 PM
 */
@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class CdrVsaDaoTest extends AbstractDaoTest
{
  @Autowired
  CdrVsaDao cdrVsaDao

  @Test
  void shouldInsert()
  {
    def time = new DateTime();

    def cdrVsa = new CdrVsa(featureId: 1,
            connectionId: "123",
            name: FeatureName.RESUME,
            forwardFromNumber: "fWN",
            status: FeatureStatus.SUCCESS,
            featureTime: time,
            forwardingReason: ForwardingReason.CALL_FWD_BUSY,
            forwardedNumber: "fN",
            forwardSourceNumber: "fSN",
            forwardToNumber: "fTN",
            calledNumber: "cN",
            callingNumber: "cIN",
            legId: "F3CA",
            originalRecord: "value")
    cdrVsaDao.persistCdrVsa(cdrVsa)

    def foundVsa = sql.firstRow("SELECT * FROM CDR_VSA WHERE CONNECTION_ID = '123'")
    assert foundVsa.ID != null
    assert "123" == foundVsa.CONNECTION_ID
    assert FeatureName.RESUME.name() == foundVsa.FEATURE_NAME
    assert "fWN" == foundVsa.FWD_FROM_NUMBER
    assert FeatureStatus.SUCCESS.name(), foundVsa.STATUS
    assert new Timestamp(time.toDate().getTime()).toString() == foundVsa.FEATURE_TIME.toString()
    assert ForwardingReason.CALL_FWD_BUSY.name(), foundVsa.FWD_REASON
    assert "fN" == foundVsa.FWD_NUMBER
    assert "fSN" == foundVsa.FWD_SRC_NUMBER
    assert "fTN" == foundVsa.FWD_TO_NUMBER
    assert "cN" == foundVsa.CALLED_NUMBER
    assert "cIN" == foundVsa.CALLING_NUMBER
    assert "F3CA" == foundVsa.LEG_ID
    assert "value" == foundVsa.ORIGINAL_RECORD
  }

  @Test
  void shouldFind()
  {
    sql.execute "INSERT INTO CDR_VSA (CONNECTION_ID, FEATURE_ID, FEATURE_TIME, FEATURE_NAME) VALUES('123', 5, '1974-09-01 17:00:00', 'RESUME')"

    def cdrVsa = cdrVsaDao.findCdrVsa("123")

    assert cdrVsa != null
    assert cdrVsa[0].connectionId == "123"
    assert cdrVsa[0].name == FeatureName.RESUME
  }
}
