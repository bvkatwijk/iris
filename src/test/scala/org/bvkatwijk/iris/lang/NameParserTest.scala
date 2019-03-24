package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class NameParserTest extends ParseTest {
  "name" - {
    "a" in name("a")
    "name" in name("name")
    "mixedCase" in name("mixedCase")

    "CompileError:" - {
      "Uppercase" in nameError("Uppercase")
    }
  }

  class LocalNameParser(val input: ParserInput) extends Parser with NameRule

  def name(value: String) = run(value) should be(Right(value))
  def run(value: String) = new IsolatedParser().parse(new LocalNameParser(value))(_.name)
  def nameError(value: String) = compileError(run(value))
}
