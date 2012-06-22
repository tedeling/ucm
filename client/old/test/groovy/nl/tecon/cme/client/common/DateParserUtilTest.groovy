package nl.tecon.cme.client.common

import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 1:24 PM
 */
class DateParserUtilTest
{
  @Test
  void shouldParseDateWithoutTimeZone()
  {
    def time = DateParserUtil.parseDateAndTimeNoTimeZone("12/16/2010 10:14:59.206")

    assert time.monthOfYear == 12
    assert time.millisOfSecond == 206
  }
}
