package nl.tecon.ucm.dataimport.syslog.dao

import org.mybatis.scala.mapping._
import nl.tecon.ucm.domain.CdrVsa

class MinimalCdrVsa {
  var id: Long = _
}

object CdrVsaDao {
  val CdrVsaMap = new ResultMap[CdrVsa] {
    id(column = "ID", property = "id")
    arg(column = "CONNECTION_ID", javaType = T[String])
  }



  val persist = new Insert[CdrVsa] {
    keyGenerator = JdbcGeneratedKey(null, "id")

    def xsql = <xsql>
      INSERT INTO CDR_VSA
      (FEATURE_ID,
      CONNECTION_ID,
      FEATURE_NAME,
      FWD_FROM_NUMBER,
      STATUS,
      FEATURE_TIME,
      FWD_REASON,
      FWD_NUMBER,
      FWD_SRC_NUMBER,
      FWD_TO_NUMBER,
      CALLED_NUMBER,
      CALLING_NUMBER,
      LEG_ID,
      ORIGINAL_RECORD)
      VALUES(#{{featureId}},
      #{{connectionId}},
      #{{name}},
      #{{forwardFromNumber}},
      #{{status}},
      #{{featureTime}},
      #{{forwardingReason}},
      #{{forwardedNumber}},
      #{{forwardSourceNumber}},
      #{{forwardToNumber}},
      #{{calledNumber}},
      #{{callingNumber}},
      #{{legId}},
      #{{originalRecord}})
    </xsql>
  }

  val findByOriginalRecord = new SelectListBy[String, MinimalCdrVsa] {
    def xsql = <xsql>
      SELECT ID
      FROM CDR_VSA
      WHERE ORIGINAL_RECORD = #{{ORIGINAL_RECORD}}
    </xsql>
  }

  def bind = Seq(findByOriginalRecord, persist)
}

