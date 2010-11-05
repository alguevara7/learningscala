package net.alexguev.learningscala.iprovingchangenotifier

import scala.xml.NodeSeq

object ChangeNodeCollector {
	
	def collect(document: NodeSeq): Seq[NodeSeq] = {
      for {
        div <- (document \\ "DIV")
        if ((div \ "@class").text == "list")
        li <- div \ "UL" \ "LI"
      } yield li
	}
	
}

//  def extractAuthor(change: NodeSeq): Option[String] = {
//    val result = change match {
//      case <LI>{ elements @_* }</LI> if (elements.head \ "@class" text) == "iconComment" => { 
//    	  Some(elements(2) \ "@title" text)
//      }
//      case _ => { 
//    	  None 
//      }
//    }
//    return result
//  }



