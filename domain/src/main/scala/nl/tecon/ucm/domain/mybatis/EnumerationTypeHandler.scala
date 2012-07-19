package nl.tecon.ucm.domain.mybatis

import org.apache.ibatis.`type`.{JdbcType, BaseTypeHandler}
import org.mybatis.scala.mapping.T
import java.sql.{ResultSet, CallableStatement, PreparedStatement}
import nl.tecon.ucm.domain.FeatureName._
import nl.tecon.ucm.domain.FeatureName
import nl.tecon.ucm.domain.FeatureName.FeatureName

class EnumerationTypeHandler extends BaseTypeHandler[FeatureName] {
  def setNonNullParameter(ps: PreparedStatement, i: Int, parameter: FeatureName.FeatureName, jdbcType: JdbcType) {}

  def getNullableResult(rs: ResultSet, columnName: String) = null

  def getNullableResult(rs: ResultSet, columnIndex: Int) = null

  def getNullableResult(cs: CallableStatement, columnIndex: Int) = null
}
