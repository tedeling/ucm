package nl.tecon.cme.client.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:21 PM
 */
public final class DateParserUtil
{
    private DateParserUtil()
    {
    }

    // formatter for 10:14:57 CET Thu Dec 16 2010
    private static final DateTimeFormatter FORMATTER_NO_MS_TZ = new DateTimeFormatterBuilder()
            .appendHourOfDay(2)
            .appendLiteral(':')
            .appendMinuteOfHour(2)
            .appendLiteral(':')
            .appendSecondOfMinute(2)
            .appendLiteral(' ')
            .appendMonthOfYearShortText()
            .appendLiteral(' ')
            .appendDayOfMonth(2)
            .appendLiteral(' ')
            .appendYear(2, 4)
            .toFormatter();

    // formatter for 10:14:57.378 CET Thu Dec 16 2010
    private static final DateTimeFormatter FORMATTER_TZ = new DateTimeFormatterBuilder()
            .appendHourOfDay(2)
            .appendLiteral(':')
            .appendMinuteOfHour(2)
            .appendLiteral(':')
            .appendSecondOfMinute(2)
            .appendLiteral('.')
            .appendMillisOfSecond(3)
            .appendLiteral(' ')
            .appendMonthOfYearShortText()
            .appendLiteral(' ')
            .appendDayOfMonth(2)
            .appendLiteral(' ')
            .appendYear(2, 4)
            .toFormatter();

    // formatter for 12/16/2010 10:14:59.206
    private static final DateTimeFormatter FORMATTER_NOTZ = new DateTimeFormatterBuilder()
            .appendMonthOfYear(2)
            .appendLiteral('/')
            .appendDayOfMonth(2)
            .appendLiteral('/')
            .appendYear(2, 4)
            .appendLiteral(' ')
            .appendHourOfDay(2)
            .appendLiteral(':')
            .appendMinuteOfHour(2)
            .appendLiteral(':')
            .appendSecondOfMinute(2)
            .appendLiteral('.')
            .appendMillisOfSecond(3)
            .toFormatter();

    /**
     * Parses date in format "10:14:57.378 CET Thu Dec 16 2010"
     *
     * @param value
     * @return
     */
    public static DateTime parseDateAndTime(String value)
    {
        return parseDateAndTimeWithTz(value, FORMATTER_TZ);
    }

    public static DateTime parseDateAndTimeWithoutMs(String value)
    {
        return parseDateAndTimeWithTz(value, FORMATTER_NO_MS_TZ);
    }

    private static DateTime parseDateAndTimeWithTz(String value, DateTimeFormatter formatter)
    {
        String[] splittedText = value.split(" ");

        StringBuffer parseByJoda = new StringBuffer(splittedText[0]);
        parseByJoda.append(" ");
        parseByJoda.append(splittedText[3]);
        parseByJoda.append(" ");
        parseByJoda.append(splittedText[4]);
        parseByJoda.append(" ");
        parseByJoda.append(splittedText[5]);

        DateTimeZone timeZone = DateTimeZone.forID(splittedText[1]);
        return formatter.parseDateTime(parseByJoda.toString()).withZone(timeZone);
    }


    public static DateTime parseDateAndTimeNoTimeZone(String value)
    {
        return FORMATTER_NOTZ.parseDateTime(value);
    }
}
