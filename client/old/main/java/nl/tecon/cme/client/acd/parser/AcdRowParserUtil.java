package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.common.ImportResultHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/24/10 2:31 PM
 */
final class AcdRowParserUtil
{
    private static final Logger LOG = Logger.getLogger(AcdRowParserUtil.class);

    // matcher for the Mon and 16:00 part in string "Mon 16:00 - 17:00"
    private static final Pattern TIME_PATTERN = Pattern.compile("^(.{3}) (\\d{2}:\\d{2}).*");

    private static final Map<String, Integer> DAY_TO_DAYNO = new HashMap<String, Integer>();

    static
    {
        DAY_TO_DAYNO.put("Mon", 1);
        DAY_TO_DAYNO.put("Tue", 2);
        DAY_TO_DAYNO.put("Wed", 3);
        DAY_TO_DAYNO.put("Thu", 4);
        DAY_TO_DAYNO.put("Fri", 5);
        DAY_TO_DAYNO.put("Sat", 6);
        DAY_TO_DAYNO.put("Sun", 7);
    }

    private AcdRowParserUtil()
    {
    }

    static DateTime offsetTime(DateMidnight base, String offset)
    {
        Matcher matcher = TIME_PATTERN.matcher(offset);

        if (matcher.matches())
        {
            String day = matcher.group(1);
            Integer dayNo = DAY_TO_DAYNO.get(day);
            Validate.notNull(dayNo, day + " is not in mon-sun");

            String timeWithColumn = matcher.group(2);
            String rawTime = StringUtils.remove(timeWithColumn, ':');

            int time = Integer.valueOf(rawTime);

            int baseDay = base.getDayOfWeek();

            // when offset day was last day of previous week
            if (dayNo > baseDay)
            {
                baseDay += 7;
            }

            int diffWithBase = dayNo - baseDay;

            DateMidnight offsettedDay = base.plusDays(diffWithBase);

            // time is in form of 1600 for 4pm
            int hours = time / 100;
            int minutes = time - (hours * 100);

            DateTime offsettedTime = offsettedDay.toDateTime().plusHours(hours).plusMinutes(minutes);

            return offsettedTime;
        }

        return null;
    }


    /**
     * @param value the int as string to parse
     * @return the string as int or null when it's not valid
     */
    static Integer parseSafelyToInteger(String value)
    {
        try
        {
            return Integer.valueOf(value);
        } catch (NumberFormatException e)
        {
            ImportResultHelper.incFailureCount();
            LOG.error("Can't set parse value " + value + " as a number");
            return null;
        }
    }

}
