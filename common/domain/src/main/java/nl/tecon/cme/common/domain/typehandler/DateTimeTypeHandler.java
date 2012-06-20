package nl.tecon.cme.common.domain.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 3:45 PM
 */
public class DateTimeTypeHandler extends BaseTypeHandler
{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        DateTime dateTime = (DateTime) parameter;

        ps.setTimestamp(i, new Timestamp(dateTime.toDate().getTime()));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        Timestamp timestamp = rs.getTimestamp(columnName);

        return timestamp != null ? new DateTime(timestamp) : null;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        Timestamp timestamp = cs.getTimestamp(columnIndex);

        return timestamp != null ? new DateTime(timestamp) : null;
    }
}
