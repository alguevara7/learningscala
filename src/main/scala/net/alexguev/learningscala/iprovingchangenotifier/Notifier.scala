package net.alexguev.learningscala.iprovingchangenotifier
import java.net.URL;

import org.cyberneko.html.parsers.SAXParser
import scala.xml.{ SAXParser, XML, NodeSeq }

class Notifier(val email: String) {

  def notifyIfNecessary(username: String, password: String) = {
    val someXML = Xml.load("http://i-proving.ca/space/snipsnap-index/Recent+Changes")
    val changes = ChangeNodeCollector.collect(someXML)

    val myChanges = changes map { new ChangeNodeParser(_) } filter { _.author == "BC Holmes" }

    println(myChanges map { _.toString } reduceLeft { _ + "\n\n" + _ })
  }

}

class ChangeNodeParser(val liContent: NodeSeq) {
  def author: String = {
    val contentInfoNode = (Xml.load(url) \\ "DIV" \ "@id") find { _.text == "ricardo-content-info" }
    contentInfoNode match {
      case Some(div) => (div \ "A")(2) text
      case None => ""
    }
  }

  def url: String = (liContent \ "A")(0) \ "@href" text
}
