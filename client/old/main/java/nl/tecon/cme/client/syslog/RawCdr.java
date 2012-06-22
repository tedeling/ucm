package nl.tecon.cme.client.syslog;

import java.util.regex.Pattern;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/22/10 11:49 AM
 */
public class RawCdr
{
    private static final Pattern SPLIT_PATTERN = Pattern.compile(",");

    private String cdr;

    public RawCdr(String cdr)
    {
        this.cdr = cdr.trim();
    }

    public String[] getSplittedWithoutType()
    {
        int firstElementAfterType = getTypeEnd() + 2;

        if (cdr.length() > firstElementAfterType)
        {
            String typeRemoved = cdr.substring(firstElementAfterType);

            String featRemoved = typeRemoved.replaceAll("FEAT_VSA=", "");

            return SPLIT_PATTERN.split(featRemoved);
        } else
        {
            return new String[0];
        }

    }

    private int getTypeBegin()
    {
        return cdr.indexOf('%');
    }

    public int getTypeEnd()
    {
        return cdr.indexOf(':', getTypeBegin());
    }

    public String getType()
    {
        int begin = getTypeBegin();
        int end = getTypeEnd();

        if (begin > 0 && end > 0)
        {
            return cdr.substring(getTypeBegin(), getTypeEnd());
        } else
        {
            return null;
        }
    }

    public String getRawCdr()
    {
        return cdr;
    }
}
