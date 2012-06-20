package nl.tecon.cme.common.domain.syslog;

import org.joda.time.DateTime;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/15/10 - 10:45 PM
 */
public class SysLog
{
    private Long id;
    private DateTime deviceTime;
    private Integer facility;
    private Integer priority;
    private String host;
    private String message;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public DateTime getDeviceTime()
    {
        return deviceTime;
    }

    public void setDeviceTime(DateTime deviceTime)
    {
        this.deviceTime = deviceTime;
    }

    public Integer getFacility()
    {
        return facility;
    }

    public void setFacility(Integer facility)
    {
        this.facility = facility;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        SysLog sysLog = (SysLog) o;

        if (id != null ? !id.equals(sysLog.id) : sysLog.id != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
