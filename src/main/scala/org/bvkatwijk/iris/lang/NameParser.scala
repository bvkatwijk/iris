package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object NameParser {
  def apply(input: ParserInput): Either[CompileError, String] = {
    new IsolatedParser().parse(new NameParser(input))(_.name)
  }
  class NameParser(val input: ParserInput) extends Parser with Base with NameRule
}


