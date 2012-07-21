package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import org.joda.time.DateTime
import java.sql.{PreparedStatement, ResultSet, CallableStatement, Timestamp}

class DateTimeTypeHandler extends BaseTypeHandler[DateTime] {
  override def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: DateTime, jdbcType: JdbcType) {
    // TODO losing timezone info
    ps.setTimestamp(i, new Timestamp(parameter.toDate.getTime))
  }

  override def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getTimestamp(columnName) != null) new DateTime(rs.getTimestamp(columnName).getTime) else null

  override def getNullableResult(rs: ResultSet, i: Int) = if (rs.getTimestamp(i) != null) new DateTime(rs.getTimestamp(i).getTime) else null

  override def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getTimestamp(columnIndex) != null) new DateTime(cs.getTimestamp(columnIndex).getTime) else null
}

