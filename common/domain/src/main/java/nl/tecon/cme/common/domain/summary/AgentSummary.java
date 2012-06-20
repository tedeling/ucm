package nl.tecon.cme.common.domain.summary;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:48 PM
 */
public class AgentSummary extends AbstractSummary
{
    private Integer agentId;
    private Agent agent;
    private Integer directCallsAnswered;
    private Integer directAvgTimeInCall;
    private Integer directLongestTimeInCall;
    private Integer directTotalCallsOnHold;
    private Integer directAvgHoldTime;
    private Integer directLongestHoldTime;
    private Integer queueCallsAnswered;
    private Integer queueAvgTimeInCall;
    private Integer queueLongestTimeInCall;
    private Integer queueTotalCallsOnHold;
    private Integer queueAvgHoldTime;
    private Integer queueLongestHoldTime;

    public Integer getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Integer agentId)
    {
        this.agentId = agentId;
    }

    public Integer getDirectCallsAnswered()
    {
        return directCallsAnswered;
    }

    public Integer getDirectAvgTimeInCall()
    {
        return directAvgTimeInCall;
    }

    public Integer getDirectLongestTimeInCall()
    {
        return directLongestTimeInCall;
    }

    public Integer getDirectTotalCallsOnHold()
    {
        return directTotalCallsOnHold;
    }

    public Integer getDirectAvgHoldTime()
    {
        return directAvgHoldTime;
    }

    public Integer getDirectLongestHoldTime()
    {
        return directLongestHoldTime;
    }

    public Integer getQueueCallsAnswered()
    {
        return queueCallsAnswered;
    }

    public Integer getQueueAvgTimeInCall()
    {
        return queueAvgTimeInCall;
    }

    public Integer getQueueLongestTimeInCall()
    {
        return queueLongestTimeInCall;
    }

    public Integer getQueueTotalCallsOnHold()
    {
        return queueTotalCallsOnHold;
    }

    public Integer getQueueAvgHoldTime()
    {
        return queueAvgHoldTime;
    }

    public Integer getQueueLongestHoldTime()
    {
        return queueLongestHoldTime;
    }

    public Agent getAgent()
    {
        return agent;
    }

    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }
}
