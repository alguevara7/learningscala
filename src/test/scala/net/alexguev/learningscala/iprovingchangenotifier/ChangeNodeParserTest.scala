package net.alexguev.learningscala.iprovingchangenotifier

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

import scala.xml.XML

class ChangeNodeParserTest extends AssertionsForJUnit {

  @Test
  def shouldParseChangeWithSingleAnchor {
    val changeContent =
      <LI>
        <A href="space/Tuesday+Lunch/blog/2010-11-01_1" title="Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010" class="iconBlogEntry">
          Tuesday Lunch - Lightning Talks: Agile Tour Toronto 2010
        </A>
      </LI>

    assertEquals("space/Tuesday+Lunch/blog/2010-11-01_1", new ChangeNodeParser(changeContent).url)
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

    assertEquals("space/Tuesday+Lunch/blog/2010-11-01_1", new ChangeNodeParser(changeContent).url)
  }

}