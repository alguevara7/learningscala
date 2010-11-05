package net.alexguev.learningscala.iprovingchangenotifier

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

class ChangeNodeCollectorTest extends AssertionsForJUnit {

  @Test def testSingleChangeScenarion {
	  val document =
		  <DIV class="list">
		  	  <H2>Recent Changes</H2>
		  	  <UL>
		  	  	<LI>
		  	  		<A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
		  	  			Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
		  	  		</A>
		  	  	</LI>
		  	  </UL>
	  	  </DIV>
	  	   
	  assertEquals(1, ChangeNodeCollector.collect(document).length)
  }

  @Test def testMultipleChangeScenarion {
	  val document =
		  <DIV class="list">
		  	  <H2>Recent Changes</H2>
		  	  <UL>
		  	  	<LI>
		  	  		<A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
		  	  			Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
		  	  		</A>
		  	  	</LI>
		  	  	<LI>
		  	  		<A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
		  	  			Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
		  	  		</A>
		  	  	</LI>
		  	  </UL>
	  	  </DIV>
	  	   
	  assertEquals(2, ChangeNodeCollector.collect(document).length)
  }
  
}