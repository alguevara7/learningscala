package net.alexguev.learningscala.iprovingchangenotifier

import java.net.URL

import scala.xml.XML
import scala.xml.NodeSeq

object Xml {

    val parser = new org.cyberneko.html.parsers.SAXParser;
    val loader = XML.withSAXParser(new scala.xml.SAXParser() {
      override def getParser() = null
      override def getProperty(name: String) = parser.getProperty(name)
      override def setProperty(name: String, value: Object) = parser.setProperty(name, value)
      override def getXMLReader() = parser
      override def isNamespaceAware() = false
      override def isValidating() = false

    })

    def load(url: String): NodeSeq = {
    	loader.load(new URL("http://i-proving.ca/space/snipsnap-index/Recent+Changes"))
    }
	
}