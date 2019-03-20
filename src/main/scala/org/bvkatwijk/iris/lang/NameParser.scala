package org.bvkatwijk.iris.lang

import org.parboiled2._

object NameParser {

  def apply(input: ParserInput): Either[CompileError, String] = {
    import Parser.DeliveryScheme.Either
    val parser = new NameParser(input)
    parser.name
      .run()
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class NameParser(val input: ParserInput) extends Parser with Base {
  def name: Rule1[String] = rule { capture(oneOrMore(CharPredicate.LowerAlpha) ~ zeroOrMore(CharPredicate.Alpha)) }
}
