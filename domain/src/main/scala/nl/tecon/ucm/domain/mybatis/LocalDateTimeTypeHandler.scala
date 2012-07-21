package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import org.joda.time.LocalDateTime
import java.sql.{CallableStatement, ResultSet, Timestamp, PreparedStatement}

class LocalDateTimeTypeHandler extends BaseTypeHandler[LocalDateTime] {
  override def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LocalDateTime, jdbcType: JdbcType) {
    ps.setTimestamp(i, new Timestamp(parameter.toDate.getTime))
  }

  override def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getTimestamp(columnName) != null) new LocalDateTime(rs.getTimestamp(columnName).getTime) else null

  override def getNullableResult(rs: ResultSet, i: Int) = if (rs.getTimestamp(i) != null) new LocalDateTime(rs.getTimestamp(i).getTime) else null

  override def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getTimestamp(columnIndex) != null) new LocalDateTime(cs.getTimestamp(columnIndex).getTime) else null

}
