package nl.tecon.ucm.dataimport.syslog.parser

import nl.tecon.ucm.domain.{Cdr, SysLog}

object CdrParser {
  def parse(syslog: SysLog) {
    val rawCdr = RawCdr(syslog.message)

    val x = rawCdr.cdrType() match {
      case Some(cdrType) if (cdrType.contains("VOIP_CALL_HISTORY"))  => cdrHistoryParser(rawCdr)
      case Some(cdrType) if (cdrType.contains("VOIP_FEAT_HISTORY"))  => vsaParser(rawCdr)
      case _ => None
    }
  }

  def cdrHistoryParser(rawCdr: RawCdr):Some[String]  = {
    val splittedCdr = rawCdr.splitWithoutType()

    splittedCdr map  (x => println(x))
    Some("")
  }

  def vsaParser(rawCdr: RawCdr):Some[String]  = {
    Some("")
  }
}
