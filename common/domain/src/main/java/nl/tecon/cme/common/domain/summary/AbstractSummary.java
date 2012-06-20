package nl.tecon.cme.common.domain.summary;

import org.joda.time.DateTime;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:43 PM
 */
public class AbstractSummary implements Entity
{
    private Integer id;
    private Integer huntGroupId;
    private DateTime summaryTime;

    public Integer getHuntGroupId()
    {
        return huntGroupId;
    }

    public void setHuntGroupId(Integer huntGroupId)
    {
        this.huntGroupId = huntGroupId;
    }

    public DateTime getSummaryTime()
    {
        return summaryTime;
    }

    public void setSummaryTime(DateTime summaryTime)
    {
        this.summaryTime = summaryTime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


}
