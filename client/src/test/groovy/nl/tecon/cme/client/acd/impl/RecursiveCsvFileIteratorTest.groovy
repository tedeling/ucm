package nl.tecon.cme.client.acd.impl

import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 1/25/11 - 1:08 AM
 */
class RecursiveCsvFileIteratorTest
{
  @Test
  void shouldScanDir()
  {
    def iterator = new RecursiveCsvFileIterator("src/test/resources/acd/fullset")

    def foundFiles = 0

    while (iterator.hasNext())
    {
      foundFiles++
      iterator.next()
    }

    assert foundFiles == 3
  }
}
