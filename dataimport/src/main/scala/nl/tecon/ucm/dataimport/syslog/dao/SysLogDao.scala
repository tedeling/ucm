package nl.tecon.ucm.dataimport.syslog.dao

import nl.tecon.ucm.domain.SysLog
import org.springframework.stereotype.Repository
import org.mybatis.spring.support.SqlSessionDaoSupport
import org.mybatis.scala.mapping.{T, SelectList, ResultMap}
import org.joda.time.DateTime


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

  val findAfterId = new SelectList[SysLog] {
    resultMap = SysLogResultMap
    def xsql = <xsql>
                  SELECT *
                  FROM SystemEvents
                  WHERE Priority = 5
                  AND Facility = 5
                </xsql>

  }

  def bind = Seq(findAfterId)
}
