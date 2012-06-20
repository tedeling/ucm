package nl.tecon.cme.common.domain.summary;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:48 PM
 */
public class QueueSummary extends AbstractSummary
{
    private Integer presentedCalls;
    private Integer answeredCalls;
    private Integer callsInQueue;
    private Integer avgTimeToAnswer;
    private Integer longestTimeToAnswer;
    private Integer abandonedCalls;
    private Integer avgTimeBeforeAbandonment;
    private Integer callsFwdToVoiceMail;
    private Integer callsAnsweredByVoiceMail;

    public Integer getPresentedCalls()
    {
        return presentedCalls;
    }

    public Integer getAnsweredCalls()
    {
        return answeredCalls;
    }

    public Integer getCallsInQueue()
    {
        return callsInQueue;
    }

    public Integer getAvgTimeToAnswer()
    {
        return avgTimeToAnswer;
    }

    public Integer getLongestTimeToAnswer()
    {
        return longestTimeToAnswer;
    }

    public Integer getAbandonedCalls()
    {
        return abandonedCalls;
    }

    public Integer getAvgTimeBeforeAbandonment()
    {
        return avgTimeBeforeAbandonment;
    }

    public Integer getCallsFwdToVoiceMail()
    {
        return callsFwdToVoiceMail;
    }

    public Integer getCallsAnsweredByVoiceMail()
    {
        return callsAnsweredByVoiceMail;
    }
}
