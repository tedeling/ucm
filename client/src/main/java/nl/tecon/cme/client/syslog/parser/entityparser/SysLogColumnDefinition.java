package nl.tecon.cme.client.syslog.parser.entityparser;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 5:26 PM
 */
interface SysLogColumnDefinition<T>
{
    String getKey();

    void setValue(String value, T entity);

    String name();

}
