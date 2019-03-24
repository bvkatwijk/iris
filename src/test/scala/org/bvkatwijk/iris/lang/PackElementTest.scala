package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.PackageElement
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class PackElementTest extends ParseTest {
  "PackElement" - {
    "accepts" - {
      "a" in packElement("a")
      "b" in packElement("b")
      "lowercase" in packElement("lowercase")
    }
  }

  class LocalPackParser(val input: ParserInput) extends Parser with PackElementRule
  def packElement(value: String) = {
    new IsolatedParser()
      .parse(new LocalPackParser(value))(_.packElement) should be(Right(PackageElement(value)))
  }
}