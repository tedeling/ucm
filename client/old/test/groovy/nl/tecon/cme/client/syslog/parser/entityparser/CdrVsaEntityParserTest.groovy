package nl.tecon.cme.client.syslog.parser.entityparser

import nl.tecon.cme.client.AbstractSpringMockContextTest
import nl.tecon.cme.client.common.ParseContext
import nl.tecon.cme.client.persistence.cdr.dao.CdrVsaDao
import nl.tecon.cme.client.syslog.RawCdr
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import nl.tecon.cme.common.domain.cdr.*
import static org.mockito.Mockito.*

/**
 * @author thies (thies@te-con.nl)
 * Date: 12/23/10 12:22 AM
 */
class CdrVsaEntityParserTest extends AbstractSpringMockContextTest
{
  @Mock
  CdrVsaDao cdrVsaDao

  CdrVsaEntityParser parser

  @Before
  void setUp()
  {
    when(context.getBean(CdrVsaDao.class)).thenReturn cdrVsaDao

    parser = new CdrVsaEntityParser()

    ParseContext.importResult.reset()
  }

  @Test
  void shouldParseForwardRecord()
  {
    ArgumentCaptor<Cdr> argument = ArgumentCaptor.forClass(Cdr.class)

    def cdr = new RawCdr(" CME-MRI: Dec 16 10:15:50.930: %VOIPAAA-5-VOIP_FEAT_HISTORY: FEAT_VSA=fn:CFA,ft:12/16/2010 10:14:59.206,frs:0,fid:113897,fcid:CA5210B082B11E0A146A279BD84410,legID:F3AC,frson:1,fdcnt:1,fwder:880888888,fwdee:00104193158,fwdto:510,frm:880888888,bguid:CA4E6727082B11E09D8300164610A2D8")

    parser.parseRecord(cdr)

    verify(cdrVsaDao).persistCdrVsa(argument.capture());

    CdrVsa vsa = argument.value

    assert vsa.name == FeatureName.CFA
    assert vsa.featureTime != null
    assert vsa.legId == "F3AC"
    assert vsa.forwardingReason == ForwardingReason.CALL_FWD
    assert vsa.status == FeatureStatus.SUCCESS
    assert vsa.featureId == 113897
    assert vsa.connectionId == "CA5210B082B11E0A146A279BD84410"
    assert vsa.forwardedNumber == "00104193158"
    assert vsa.forwardSourceNumber == "880888888"
    assert vsa.forwardToNumber == "510"
    assert vsa.forwardFromNumber == "880888888"
  }

  @Test
  void shouldParseTWCRecord()
  {
    ArgumentCaptor<Cdr> argument = ArgumentCaptor.forClass(Cdr.class)

    def cdr = new RawCdr(" CME-MRI: Dec 16 10:15:50.930: %VOIPAAA-5-VOIP_FEAT_HISTORY: FEAT_VSA=fn:TWC,ft:12/16/2010 10:14:59.190,cgn:104193158,cdn:880888888,frs:0,fid:113894,fcid:CA4E672782B11E09D8300164610A2D8,legID:F3AB,bguid:CA4E6727082B11E09D8300164610A2D8")

    parser.parseRecord(cdr)

    verify(cdrVsaDao).persistCdrVsa(argument.capture());

    CdrVsa vsa = argument.value

    assert vsa.name == FeatureName.TWC
    assert vsa.calledNumber == "880888888"
    assert vsa.callingNumber == "104193158"
  }

  @Test
  void shouldFailParseRecord()
  {
    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:15:50.930: %VOIPAAA-5-VOIP_FEAT_HISTORY: FEAT_VSA=fn:TWC,ft:1INVALID0,cgn:104193158,cdn:880888888,frs:0,fid:113894,fcid:CA4E672782B11E09D8300164610A2D8,legID:F3AB,bguid:CA4E6727082B11E09D8300164610A2D8")

    parser.parseRecord(rawCdr)

    verifyZeroInteractions(cdrVsaDao)

    assert ParseContext.importResult.importFailureCount == 1
  }

  @Test
  void shouldWarnParseRecord()
  {
    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_FEAT_HISTORY: fcid:0000")

    parser.parseRecord(rawCdr)

    verifyZeroInteractions(cdrVsaDao)

    assert ParseContext.importResult.importWarningCount == 1
  }

  @Test
  void shouldFailOnEmptyRecord()
  {
    def rawCdr = new RawCdr(" CME-MRI: Dec 16 10:16:25.486: %VOIPAAA-5-VOIP_FEAT_HISTORY: ")

    parser.parseRecord(rawCdr)

    verifyZeroInteractions(cdrVsaDao)

    assert ParseContext.importResult.importFailureCount == 1
  }
}
