package nl.tecon.cme.common.domain.cdr;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/18/10 11:54 PM
 */
public enum ForwardingReason
{
    UNKNOWN("0"),
    CALL_FWD("1"),
    CALL_FWD_BUSY("2"),
    CALL_FWD_NO_REPLY("3"),
    CALL_DEFLECTION("4");

    private String rawValue;

    ForwardingReason(String rawValue)
    {
        this.rawValue = rawValue;
    }

    String getRawValue()
    {
        return rawValue;
    }

    public static ForwardingReason rawValueFor(String rawValue)
    {
        for (ForwardingReason reason : ForwardingReason.values())
        {
            if (reason.getRawValue().equalsIgnoreCase(rawValue))
            {
                return reason;
            }
        }

        return null;
    }

}
