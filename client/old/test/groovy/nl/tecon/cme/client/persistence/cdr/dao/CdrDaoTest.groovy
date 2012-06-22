package nl.tecon.cme.client.persistence.cdr.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.cdr.Cdr
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 3:28 PM
 */
@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class CdrDaoTest extends AbstractDaoTest
{
  @Autowired
  CdrDao cdrDao

  @Test
  void shouldInsert()
  {
    def cdr = new Cdr(callLegType: 1,
            connectionId: "123",
            setUpTime: new DateTime(),
            peerAddress: "pA",
            peerSubAddress: "pSA",
            disconnectCause: "dC",
            disconnectText: "dT",
            connectTime: new DateTime(),
            disconnectTime: new DateTime(),
            callOrigin: "cO",
            chargedUnits: "cU",
            infoType: "iT",
            transmitPackets: 2l,
            transmitBytes: 3l,
            receivedPackets: 4l,
            receivedBytes: 5l,
            originalRecord: "value")
    cdrDao.persistCdr(cdr)

    def foundCdr = sql.firstRow("SELECT * FROM CDR WHERE CONNECTION_ID = '123'")
    assert foundCdr.ID != null
    assert 1 == foundCdr.CALL_LEG_TYPE
    assert "123" == foundCdr.CONNECTION_ID
    assert "value" == foundCdr.ORIGINAL_RECORD
    assert foundCdr.SETUP_TIME != null
    assert "pA", foundCdr.PEER_ADDRESS
    assert "pSA", foundCdr.PEER_SUBADDRESS
    assert foundCdr.CONNECT_TIME != null
    assert foundCdr.DISCONNECT_TIME != null
    assert "cO" == foundCdr.CALL_ORIGIN
    assert "cU" == foundCdr.CHARGED_UNITS
    assert "iT" == foundCdr.INFO_TYPE
    assert 2 == foundCdr.TRANSMIT_PACKETS
    assert 3 == foundCdr.TRANSMIT_BYTES
    assert 4 == foundCdr.RECEIVED_PACKETS
    assert 5 == foundCdr.RECEIVED_BYTES
    assert "value" == foundCdr.ORIGINAL_RECORD
  }

  @Test
  void shouldFind()
  {
    sql.execute "INSERT INTO CDR (CONNECTION_ID, SETUP_TIME, ORIGINAL_RECORD) VALUES('123', '1974-09-01 17:00:00', '123')"

    def cdr = cdrDao.findCdr("123")

    assert cdr != null
    assert cdr[0].connectionId == "123"
  }
}
