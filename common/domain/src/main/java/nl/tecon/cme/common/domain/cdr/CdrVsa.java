package nl.tecon.cme.common.domain.cdr;

import org.joda.time.DateTime;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/18/10 11:38 PM
 */
public class CdrVsa extends AbstractCdr
{
    private static final long serialVersionUID = 8640993644653682880L;

    private Long cdrVsaId;
    // fid
    private Long featureId;
    // legId
    private String legId;
    // fcid
//    private String connectionId;
    // fn
    private FeatureName name;
    // frm
    private String forwardFromNumber;
    // frs
    private FeatureStatus status;
    // ft
    private DateTime featureTime;
    // frson
    private ForwardingReason forwardingReason;
    // fwdee
    private String forwardedNumber;
    // fwder
    private String forwardSourceNumber;
    // fwdto
    private String forwardToNumber;
    // cdn
    private String calledNumber;
    // cgn
    private String callingNumber;

    private String originalRecord;

    public String getOriginalRecord()
    {
        return originalRecord;
    }

    public void setOriginalRecord(String originalRecord)
    {
        this.originalRecord = originalRecord;
    }

    public Long getCdrVsaId()
    {
        return cdrVsaId;
    }

    public void setCdrVsaId(Long cdrVsaId)
    {
        this.cdrVsaId = cdrVsaId;
    }

    public Long getFeatureId()
    {
        return featureId;
    }

    public void setFeatureId(Long featureId)
    {
        this.featureId = featureId;
    }

    public FeatureName getName()
    {
        return name;
    }

    public void setName(FeatureName name)
    {
        this.name = name;
    }

    public String getForwardFromNumber()
    {
        return forwardFromNumber;
    }

    public void setForwardFromNumber(String forwardFromNumber)
    {
        this.forwardFromNumber = forwardFromNumber;
    }

    public FeatureStatus getStatus()
    {
        return status;
    }

    public void setStatus(FeatureStatus status)
    {
        this.status = status;
    }

    public DateTime getFeatureTime()
    {
        return featureTime;
    }

    public void setFeatureTime(DateTime featureTime)
    {
        this.featureTime = featureTime;
    }

    public ForwardingReason getForwardingReason()
    {
        return forwardingReason;
    }

    public void setForwardingReason(ForwardingReason forwardingReason)
    {
        this.forwardingReason = forwardingReason;
    }

    public String getForwardedNumber()
    {
        return forwardedNumber;
    }

    public void setForwardedNumber(String forwardedNumber)
    {
        this.forwardedNumber = forwardedNumber;
    }

    public String getForwardSourceNumber()
    {
        return forwardSourceNumber;
    }

    public void setForwardSourceNumber(String forwardSourceNumber)
    {
        this.forwardSourceNumber = forwardSourceNumber;
    }

    public String getForwardToNumber()
    {
        return forwardToNumber;
    }

    public void setForwardToNumber(String forwardToNumber)
    {
        this.forwardToNumber = forwardToNumber;
    }

    public String getCalledNumber()
    {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber)
    {
        this.calledNumber = calledNumber;
    }

    public String getCallingNumber()
    {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber)
    {
        this.callingNumber = callingNumber;
    }

    public String getLegId()
    {
        return legId;
    }

    public void setLegId(String legId)
    {
        this.legId = legId;
    }
}
