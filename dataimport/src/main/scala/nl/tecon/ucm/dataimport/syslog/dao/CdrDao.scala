package nl.tecon.ucm.dataimport.syslog.dao

import org.mybatis.scala.mapping._
import nl.tecon.ucm.domain.Cdr

class MinimalCdr
{
  var id: Long = _
}

object CdrDao {
  val CdrMap = new ResultMap[Cdr] {
    id(column = "ID", property = "id")
    arg(column = "CONNECTION_ID", javaType = T[String])
  }

  val persist = new Insert[Cdr] {
    keyGenerator = JdbcGeneratedKey(null, "id")

    def xsql = <xsql>
      INSERT INTO CDR
      (CALL_LEG_TYPE, CONNECTION_ID, SETUP_TIME, PEER_ADDRESS, PEER_SUB_ADDRESS,
      DISCONNECT_CAUSE, DISCONNECT_TEXT, CONNECT_TIME, DISCONNECT_TIME, CALL_ORIGIN, CHARGED_UNITS,
      INFO_TYPE, TRANSMIT_PACKETS, TRANSMIT_BYTES, RECEIVED_PACKETS, RECEIVED_BYTES,
      ORIGINAL_RECORD)
      VALUES(#{{callLegType}}, #{{connectionId}}, #{{setUpTime}}, #{{peerAddress}}, #{{peerSubAddress}}, #{{disconnectCause}},
      #{{disconnectText}}, #{{connectTime}}, #{{disconnectTime}}, #{{callOrigin}}, #{{chargedUnits}}, #{{infoType}}, #{{transmitPackets}},
      #{{transmitBytes}}, #{{receivedPackets}}, #{{receivedBytes}}, #{{originalRecord}})
    </xsql>
  }

  val findByOriginalRecord = new SelectListBy[String, MinimalCdr] {
    def xsql = <xsql>
      SELECT ID
      FROM CDR
      WHERE ORIGINAL_RECORD = #{{ORIGINAL_RECORD}}
    </xsql>
  }

  def bind = Seq(findByOriginalRecord, persist)
}
