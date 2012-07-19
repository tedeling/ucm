package nl.tecon.ucm.dataimport.syslog.parser

import org.specs2.mutable.SpecificationWithJUnit
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics
import nl.tecon.ucm.domain.{FeatureStatus, CdrVsa, FeatureName}
import org.joda.time.{LocalDateTime, DateTime}

class CdrVsaBuilderSpec extends SpecificationWithJUnit {
  "The CdrVsaBuilder" should {

    "parse a raw CDR VSA properly" in {
      implicit val stats = new SysLogParsingStatistics

      val x = "fn:TWC,ft:01/05/2011 15:27:08.722,cgn:00681528045,cdn:510,frs:0,fid:164445,fcid:B63D1ED6180E11E08611A279BD84410,legID:1615F,bguid:B63D1ED6180E11E08611A2790BD84410"

      val cdr = "original"
      val builder = new CdrVsaBuilder(cdr)

      builder.parse("fn:TWC")
      builder.parse("ft:01/05/2011 15:27:08.722")
      builder.parse("cgn:00681528045")
      builder.parse("cdn:510")
      builder.parse("frs:0")
      builder.parse("fid:164445")
      builder.parse("fcid:B63D1ED6180E11E08611A279BD84410")
      builder.parse("legID:1615F")
      builder.parse("bguid:B63D1ED6180E11E08611A2790BD84410")

      builder.build() === Some(CdrVsa(connectionId = "B63D1ED6180E11E08611A279BD84410",
        name = FeatureName.TWC,
        featureTime = new LocalDateTime(2011, 1, 5, 15, 27, 8, 722),
        callingNumber = "00681528045",
        calledNumber = "510",
        status = FeatureStatus.FAIL,
        featureId = 164445,
        legId = "1615F",
        originalRecord = cdr) )
    }
  }
}
