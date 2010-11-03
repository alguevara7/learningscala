package net.alexguev.learningscala.iprovingchangenotifier
import java.net.URL;

import org.cyberneko.html.parsers.SAXParser
import scala.xml.{SAXParser, XML, NodeSeq}

class Notifier(val email: String) {

  def notifyIfNecessary() = {
    val parser = new org.cyberneko.html.parsers.SAXParser;
    val loader = XML.withSAXParser(new scala.xml.SAXParser() {
      override def getParser() = null
      override def getProperty(name: String) = parser.getProperty(name)
      override def setProperty(name: String, value: Object) = parser.setProperty(name, value)
      override def getXMLReader() = parser
      override def isNamespaceAware() = false
      override def isValidating() = false

    })

    val someXML = loader.load(new URL("http://i-proving.ca/space/snipsnap-index/Recent+Changes"))

    val changes =
	    for {div <- (someXML \\ "DIV" ) 
	    	if ((div \ "@class").text == "list") 
	    	change <- div \ "UL" \ "LI" } yield change
    

    println( changes.map{_.toString}.reduceLeft{(left: String, right: String) => left + "\n\n" + right} )
	println( "\n\n" )	
	println(changes filter mine)

  }
  
  def mine(change: NodeSeq) = autoredBy(change, matchingExtractor(change)) == "Alexei Guevara"
	 
  def autoredBy(change: NodeSeq, authorExtractor: NodeSeq => String) = authorExtractor(change)
  
  def matchingExtractor(change: NodeSeq): NodeSeq => String = change match {
	  case <LI><A>{_*}</A><A>{author @ _*}</A></LI> => {
	 	  change => (author \ "@title") text
	  }
	  case _ => {
		  change => ""
	  }
  }
  
  case class Change(val title: String, kind: String, href: String) {}

}