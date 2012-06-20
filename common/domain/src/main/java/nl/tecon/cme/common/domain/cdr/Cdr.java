package nl.tecon.cme.common.domain.cdr;

import org.joda.time.DateTime;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/18/10 11:09 PM
 */
public class Cdr extends AbstractCdr
{
    private static final long serialVersionUID = -9020897578188662360L;

    private Long cdrId;
    private Integer callLegType;
    private DateTime setUpTime;
    private String peerAddress;
    private String peerSubAddress;
    private String disconnectCause;
    private String disconnectText;
    private DateTime connectTime;
    private DateTime disconnectTime;
    private String callOrigin;
    private String chargedUnits;
    private String infoType;
    private Long transmitPackets;
    private Long transmitBytes;
    private Long receivedPackets;
    private Long receivedBytes;
    private String originalRecord;

    public Long getCdrId()
    {
        return cdrId;
    }

    public DateTime getDisconnectTime()
    {
        return disconnectTime;
    }

    public void setDisconnectTime(DateTime disconnectTime)
    {
        this.disconnectTime = disconnectTime;
    }

    public void setCdrId(Long cdrId)
    {
        this.cdrId = cdrId;
    }

    public String getOriginalRecord()
    {
        return originalRecord;
    }

    public void setOriginalRecord(String originalRecord)
    {
        this.originalRecord = originalRecord;
    }

    public Integer getCallLegType()
    {
        return callLegType;
    }

    public void setCallLegType(Integer callLegType)
    {
        this.callLegType = callLegType;
    }

    public DateTime getSetUpTime()
    {
        return setUpTime;
    }

    public void setSetUpTime(DateTime setUpTime)
    {
        this.setUpTime = setUpTime;
    }

    public String getPeerAddress()
    {
        return peerAddress;
    }

    public void setPeerAddress(String peerAddress)
    {
        this.peerAddress = peerAddress;
    }

    public String getPeerSubAddress()
    {
        return peerSubAddress;
    }

    public void setPeerSubAddress(String peerSubAddress)
    {
        this.peerSubAddress = peerSubAddress;
    }

    public String getDisconnectCause()
    {
        return disconnectCause;
    }

    public void setDisconnectCause(String disconnectCause)
    {
        this.disconnectCause = disconnectCause;
    }

    public String getDisconnectText()
    {
        return disconnectText;
    }

    public void setDisconnectText(String disconnectText)
    {
        this.disconnectText = disconnectText;
    }

    public DateTime getConnectTime()
    {
        return connectTime;
    }

    public void setConnectTime(DateTime connectTime)
    {
        this.connectTime = connectTime;
    }

    public String getCallOrigin()
    {
        return callOrigin;
    }

    public void setCallOrigin(String callOrigin)
    {
        this.callOrigin = callOrigin;
    }

    public String getChargedUnits()
    {
        return chargedUnits;
    }

    public void setChargedUnits(String chargedUnits)
    {
        this.chargedUnits = chargedUnits;
    }

    public String getInfoType()
    {
        return infoType;
    }

    public void setInfoType(String infoType)
    {
        this.infoType = infoType;
    }

    public Long getTransmitPackets()
    {
        return transmitPackets;
    }

    public void setTransmitPackets(Long transmitPackets)
    {
        this.transmitPackets = transmitPackets;
    }

    public Long getTransmitBytes()
    {
        return transmitBytes;
    }

    public void setTransmitBytes(Long transmitBytes)
    {
        this.transmitBytes = transmitBytes;
    }

    public Long getReceivedPackets()
    {
        return receivedPackets;
    }

    public void setReceivedPackets(Long receivedPackets)
    {
        this.receivedPackets = receivedPackets;
    }

    public Long getReceivedBytes()
    {
        return receivedBytes;
    }

    public void setReceivedBytes(Long receivedBytes)
    {
        this.receivedBytes = receivedBytes;
    }
}
