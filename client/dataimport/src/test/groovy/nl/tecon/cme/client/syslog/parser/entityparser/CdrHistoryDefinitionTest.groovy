package nl.tecon.cme.client.syslog.parser.entityparser

import nl.tecon.cme.common.domain.cdr.Cdr
import org.joda.time.DateTimeZone
import org.junit.Test

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/22/10 12:36 PM
 */
class CdrHistoryDefinitionTest
{
  @Test
  void shouldParseSetupTime()
  {
    def timeToParse = "10:14:57.378 CET Thu Dec 16 2010"

    def tz = DateTimeZone.forID("CET")

    def cdr = new Cdr()

    CdrHistoryDefinition.SETUP_TIME.setValue(timeToParse, cdr)

    assert cdr.setUpTime.dayOfMonth == 16
    assert cdr.setUpTime.monthOfYear == 12
    assert cdr.setUpTime.year == 2010
    assert cdr.setUpTime.hourOfDay == 10
    assert cdr.setUpTime.minuteOfHour == 14
    assert cdr.setUpTime.secondOfMinute == 57
    assert cdr.setUpTime.millisOfSecond == 378
    assert cdr.setUpTime.dayOfWeek == 4 // monday = 1
    assert cdr.setUpTime.zone == tz
  }
}
