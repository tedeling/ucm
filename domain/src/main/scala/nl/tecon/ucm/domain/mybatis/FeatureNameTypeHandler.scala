package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import java.sql.{ResultSet, CallableStatement, PreparedStatement}
import nl.tecon.ucm.domain.FeatureName

class FeatureNameTypeHandler extends BaseTypeHandler[FeatureName] {
  def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: FeatureName, jdbcType: JdbcType) {
    ps.setString(i, parameter.toString)
  }

  def getNullableResult(rs: ResultSet, columnName: String) = if (rs.getString(columnName) != null) FeatureName.withName(rs.getString(columnName)) else null

  def getNullableResult(rs: ResultSet, columnIndex: Int) = if (rs.getString(columnIndex) != null) FeatureName.withName(rs.getString(columnIndex)) else null

  def getNullableResult(cs: CallableStatement, columnIndex: Int) = if (cs.getString(columnIndex) != null) FeatureName.withName(cs.getString(columnIndex)) else null
}
