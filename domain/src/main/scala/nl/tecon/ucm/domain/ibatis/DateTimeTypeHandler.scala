package nl.tecon.ucm.domain.ibatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import org.joda.time.DateTime
import java.sql.{PreparedStatement, ResultSet, CallableStatement, Timestamp}

class DateTimeTypeHandler extends BaseTypeHandler {
  override def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: AnyRef, jdbcType: JdbcType) {
    ps.setTimestamp(i, new Timestamp(parameter.asInstanceOf[DateTime].toDate.getTime))
  }

  override def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getTimestamp(columnName) != null) new DateTime(rs.getTimestamp(columnName)) else null

  override def getNullableResult(resultSet: ResultSet, i: Int) = if (resultSet.getTimestamp(i) != null) new DateTime(resultSet.getTimestamp(i)) else null

  override def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getTimestamp(columnIndex) != null) new DateTime(cs.getTimestamp(columnIndex)) else null
}

