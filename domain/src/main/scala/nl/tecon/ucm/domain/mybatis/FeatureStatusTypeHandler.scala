package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import java.sql.{ResultSet, CallableStatement, PreparedStatement}
import nl.tecon.ucm.domain.FeatureStatus

class FeatureStatusTypeHandler extends BaseTypeHandler[FeatureStatus] {
  def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: FeatureStatus, jdbcType: JdbcType) {
    ps.setString(i, parameter.toString)
  }

  def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getString(columnName) != null) FeatureStatus.withName(rs.getString(columnName)) else null

  def getNullableResult(rs: ResultSet, columnIndex: Int) = if (rs.getString(columnIndex) != null) FeatureStatus.withName(rs.getString(columnIndex)) else null

  def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getString(columnIndex) != null) FeatureStatus.withName(cs.getString(columnIndex)) else null
}
