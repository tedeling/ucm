package nl.tecon.ucm.dataimport.syslog.dao

import nl.tecon.ucm.domain.{Cdr, SysLog}
import org.mybatis.scala.mapping._
import org.joda.time.DateTime
import nl.tecon.ucm.domain.Cdr
import nl.tecon.ucm.domain.SysLog


object SysLogDao {
  val SysLogResultMap = new ResultMap[SysLog] {
    id ( column = "ID", property = "id" )
    arg ( column = "ID", javaType = T[Long])
    arg ( column = "DeviceReportedTime", javaType = T[DateTime])
    arg ( column = "Facility", javaType = T[Int])
    arg ( column = "Priority", javaType = T[Int])
    arg ( column = "FromHost", javaType = T[String])
    arg ( column = "Message", javaType = T[String])
  }

  val findAfterId = new SelectListBy[Long, SysLog] {
    resultMap = SysLogResultMap
    def xsql = <xsql>
                  SELECT *
                  FROM SystemEvents
                  WHERE ID >= #{{id}} AND Priority = 5
                  AND Facility = 5
                </xsql>
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

  def bind = Seq(findAfterId)
}
