package net.alexguev.learningscala.iprovingchangenotifier
import java.net.URL;

import org.cyberneko.html.parsers.SAXParser
import scala.xml.{ SAXParser, XML, NodeSeq }

class Notifier(val email: String) {

//  def notifyIfNecessary(username: String, password: String) = {
//    val someXML = XmlLoader.loadFromUrl("http://i-proving.ca/space/snipsnap-index/Recent+Changes")
//
//    val myChanges = ChangeNodeCollector.collect(someXML).
//      map { new ChangeNodeParser(_) }.
//      filter { _.author == "BC Holmes" }
//
//    println(myChanges map { _.toString } reduceLeft { _ + "\n\n" + _ })
//  }

}
