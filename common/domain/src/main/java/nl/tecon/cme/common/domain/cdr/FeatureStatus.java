package nl.tecon.cme.common.domain.cdr;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/18/10 11:49 PM
 */
public enum FeatureStatus
{
    SUCCESS("0"),
    FAIL("1");

    private String rawValue;

    String getRawValue()
    {
        return rawValue;
    }

    FeatureStatus(String rawValue)
    {
        this.rawValue = rawValue;
    }

    public static FeatureStatus rawValueFor(String rawValue)
    {
        for (FeatureStatus status : FeatureStatus.values())
        {
            if (status.getRawValue().equalsIgnoreCase(rawValue))
            {
                return status;
            }
        }

        return null;
    }
}
