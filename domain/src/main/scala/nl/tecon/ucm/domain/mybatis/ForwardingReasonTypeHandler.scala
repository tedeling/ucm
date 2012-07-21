package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import java.sql.{ResultSet, CallableStatement, PreparedStatement}
import nl.tecon.ucm.domain.ForwardingReason
import org.mybatis.scala.mapping.OptionTypeHandler

class ForwardingReasonTypeHandler extends BaseTypeHandler[ForwardingReason] {
  def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: ForwardingReason, jdbcType: JdbcType) {
    ps.setString(i, parameter.toString)
  }

  def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getString(columnName) != null) ForwardingReason.withName(rs.getString(columnName)) else null

  def getNullableResult(rs: ResultSet, columnIndex: Int) = if (rs.getString(columnIndex) != null) ForwardingReason.withName(rs.getString(columnIndex)) else null

  def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getString(columnIndex) != null) ForwardingReason.withName(cs.getString(columnIndex)) else null
}

class OptForwardingReasonTypeHandler extends OptionTypeHandler(new ForwardingReasonTypeHandler())
