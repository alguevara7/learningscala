package net.alexguev.learningscala.iprovingchangenotifier

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import org.scalatest.mock.MockitoSugar
import org.mockito._
import org.mockito.Mockito._

import scala.xml.XML

trait Environment extends XmlLoaderComponent with ChangeNodeParserComponent with MockitoSugar {
	override val xmlLoader = mock[XmlLoader]
}

class ChangeNodeParserTest extends AssertionsForJUnit with Environment {
	
  @Test
  def shouldParseChangeWithSingleAnchor {
    val changeContent =
      <LI>
        <A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
          Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
        </A>
      </LI>
    	
    when(xmlLoader.loadFromSecuredUrl("space/Tuesday+Lunch/blog/2010-11-01_1", "guevaraa", "")).thenReturn(
    	<DIV id="ricardo-content-info">Created by 
    		<A href="space/Lawrence+Ludlow">John Doe</A>. Last edited by 
    		<A href="space/Lawrence+Ludlow">John Doe</A>, 2 days ago.
    	</DIV>
    )
    	
    assertEquals("url", Some("space/Tuesday+Lunch/blog/2010-11-01_1"), new ChangeNodeParser(changeContent).url)
    assertEquals("author", Some("John Doe"), new ChangeNodeParser(changeContent).author)
  }

  @Test
  def shouldParseChangeWithMultipleAnchor {
    val changeContent =
      <LI>
        <A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
          Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
        </A>
        <A href="none" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
          Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
        </A>
      </LI>

    assertEquals(Some("space/Tuesday+Lunch/blog/2010-11-01_1"), new ChangeNodeParser(changeContent).url)
  }

  @Test
  def shouldParseChangeWithMultipleAnchor1 {
    val changeContent =
      <LI>
      </LI>

    assertEquals(None, new ChangeNodeParser(changeContent).url)
  }
  
}