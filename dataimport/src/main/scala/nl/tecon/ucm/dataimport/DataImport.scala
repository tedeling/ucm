package nl.tecon.ucm.dataimport

import org.springframework.stereotype.Service
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import syslog.SysLogImport

@Service
class DataImport @Autowired()(sysLogImport: SysLogImport) {
  private val LOG = Logger.getLogger(classOf[DataImport])

  def startImport() {
    parseCdr()
  }


  private def parseCdr() {
    LOG.info("*** Stage 1: Parsing CDR's from Syslog")
    sysLogImport.parseSysLog()
    //    val importResult: ImportResult = getResult
    //    LOG.info("*** Stage 1: completed. Records successfully parsed: " + importResult.getImportSuccessCount + ", failures: " + importResult.getImportFailureCount + ", warnings: " + importResult.getImportWarningCount)
  }

}
