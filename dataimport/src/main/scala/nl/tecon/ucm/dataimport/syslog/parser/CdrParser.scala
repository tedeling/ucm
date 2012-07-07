package nl.tecon.ucm.dataimport.syslog.parser

import nl.tecon.ucm.domain.{Cdr, SysLog}
import nl.tecon.ucm.dataimport.syslog.SysLogParsingStatistics
import nl.tecon.ucm.dataimport.syslog.dao.{CdrDao, SysLogDao}
import org.mybatis.scala.session.Session
import org.apache.log4j.Logger

object CdrParser {
  private val LOG: Logger = Logger.getLogger(classOf[CdrBuilder])

  def parse(syslog: SysLog)(implicit stats: SysLogParsingStatistics, session:Session) {
    val rawCdr = RawCdr(syslog.message)

    rawCdr.cdrType() match {
      case Some(cdrType) if (cdrType.contains("VOIP_CALL_HISTORY")) => persist(cdrHistoryParser(rawCdr))
      case Some(cdrType) if (cdrType.contains("VOIP_FEAT_HISTORY")) => vsaParser(rawCdr)
      case _ => None
    }
  }

  def persist(cdr: Option[Cdr])(implicit session: Session) {
    cdr match {
      case Some(c) => {
        val record = CdrDao.findByOriginalRecord(c.originalRecord)

        if (record.isEmpty)
          CdrDao.persist(c)
        else
          LOG.warn("Record already exists %s".format(c.originalRecord))
      }
      case None =>
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
