package nl.tecon.ucm.dataimport

import org.springframework.context.support.ClassPathXmlApplicationContext

object DataImportBootstrap {

  def main(args:Array[String]) {
    val context = new ClassPathXmlApplicationContext("context-root.xml")
    val dataImport = context.getBean(classOf[DataImport])

    dataImport.startImport()
  }
}
