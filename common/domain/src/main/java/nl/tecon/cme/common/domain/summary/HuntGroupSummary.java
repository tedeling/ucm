package nl.tecon.cme.common.domain.summary;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:48 PM
 */
public class HuntGroupSummary extends AbstractSummary
{
    private Integer huntGroupId;
    private Integer agentCountMax;
    private Integer agentCountMin;
    private Integer callCount;
    private Integer answeredCount;
    private Integer abandonedCount;
    private Integer avgTimeToAnswer;
    private Integer longestTimeToAnswer;
    private Integer avgTimeInCall;
    private Integer longestTimeInCall;
    private Integer avgTimeBeforeAbandonment;
    private Integer callsOnHold;
    private Integer avgTimeOnHold;
    private Integer longestTimeOnHold;

    public Integer getAgentCountMax()
    {
        return agentCountMax;
    }

    public Integer getAgentCountMin()
    {
        return agentCountMin;
    }

    public Integer getCallCount()
    {
        return callCount;
    }

    public Integer getAnsweredCount()
    {
        return answeredCount;
    }

    public Integer getAbandonedCount()
    {
        return abandonedCount;
    }

    public Integer getAvgTimeToAnswer()
    {
        return avgTimeToAnswer;
    }

    public Integer getLongestTimeToAnswer()
    {
        return longestTimeToAnswer;
    }

    public Integer getAvgTimeInCall()
    {
        return avgTimeInCall;
    }

    public Integer getLongestTimeInCall()
    {
        return longestTimeInCall;
    }

    public Integer getAvgTimeBeforeAbandonment()
    {
        return avgTimeBeforeAbandonment;
    }

    public Integer getCallsOnHold()
    {
        return callsOnHold;
    }

    public Integer getAvgTimeOnHold()
    {
        return avgTimeOnHold;
    }

    public Integer getLongestTimeOnHold()
    {
        return longestTimeOnHold;
    }


    public Integer getHuntGroupId()
    {
        return huntGroupId;
    }

    public void setHuntGroupId(Integer huntGroupId)
    {
        this.huntGroupId = huntGroupId;
    }
}
