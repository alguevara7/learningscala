package net.alexguev.learningscala.iprovingchangenotifier
import java.net.URL;

import org.cyberneko.html.parsers.SAXParser
import scala.xml.{ SAXParser, XML, NodeSeq }

class Notifier(val email: String) extends XmlLoaderComponent with ChangeNodeParserComponent {

  def notifyIfNecessary(username: String, password: String) = {
    val someXML = xmlLoader.loadFromUrl("http://i-proving.ca/space/snipsnap-index/Recent+Changes")

    val myChanges = ChangeNodeCollector.collect(someXML).
      map { new ChangeNodeParser(_) }.//map { p => println(p.author); p }.
      filter { _.icon != Some("iconTag") }.
      filter { _.author == Some("Alexei Guevara") }
      
    println(myChanges map { _.toString } reduceLeft { _ + "\n\n" + _ })
  }
  
}
