package nl.tecon.ucm.dataimport.syslog.parser

import nl.tecon.ucm.domain.{Cdr, SysLog}
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics

object CdrParser {
  def parse(syslog: SysLog)(implicit stats: SysLogParsingStatistics) {
    val rawCdr = RawCdr(syslog.message)

    val x = rawCdr.cdrType() match {
      case Some(cdrType) if (cdrType.contains("VOIP_CALL_HISTORY")) => cdrHistoryParser(rawCdr)
      case Some(cdrType) if (cdrType.contains("VOIP_FEAT_HISTORY")) => vsaParser(rawCdr)
      case _ => None
    }
  }

  def cdrHistoryParser(rawCdr: RawCdr)(implicit stats: SysLogParsingStatistics): Option[Cdr] = {

    val builder = new CdrBuilder(rawCdr.cdr)

    val splittedCdr = rawCdr.splitWithoutType()

    splittedCdr map (keyValue => {
      builder.parse(keyValue)
    })

    builder.build()
  }

  def vsaParser(rawCdr: RawCdr): Some[String] = {
    Some("")
  }
}
