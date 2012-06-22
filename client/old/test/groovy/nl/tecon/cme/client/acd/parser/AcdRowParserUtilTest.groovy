package nl.tecon.cme.client.acd.parser

import org.joda.time.DateTime
import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 2/13/11 - 11:07 PM
 */
class AcdRowParserUtilTest {
  @Test
  void shouldSetOffsetAsDate()
  {
    // tuesday
    def base = new DateTime(2011, 2, 8, 23, 0, 0, 0);

    def source = "Mon 12:00 - 13:00"

    def offsettedTime = AcdRowParserUtil.offsetTime(base.toDateMidnight(), source)

    assert offsettedTime.year == 2011
    assert offsettedTime.monthOfYear == 2
    assert offsettedTime.dayOfMonth == 7
    assert offsettedTime.hourOfDay == 12
    assert offsettedTime.minuteOfHour == 0
  }

    @Test
  void shouldSetOffsetAsDateWithWeekOverlap()
  {
    // tuesday
    def base = new DateTime(2011, 2, 14, 23, 0, 0, 0);

    def source = "Sun 12:00 - 13:00"

    def offsettedTime = AcdRowParserUtil.offsetTime(base.toDateMidnight(), source)

    assert offsettedTime.year == 2011
    assert offsettedTime.monthOfYear == 2
    assert offsettedTime.dayOfMonth == 13
    assert offsettedTime.hourOfDay == 12
    assert offsettedTime.minuteOfHour == 0
  }

}
