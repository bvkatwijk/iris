package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Identifier, QualifiedIdentifier}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object IdentifierParser {
  def apply(input: ParserInput): Either[CompileError, QualifiedIdentifier] = {
    new IsolatedParser().parse(new IdentifierParser(input))(_.qualifiedIdentifier)
  }

  def identifier(input: ParserInput): Either[CompileError, Identifier] = {
    new IsolatedParser().parse(new IdentifierParser(input))(_.captureIdentifier)
  }
}

class IdentifierParser(val input: ParserInput) extends Parser with Base {
  def qualifiedIdentifier: Rule1[QualifiedIdentifier] = rule { capture(zeroOrMore(pack ~ '.') ~ identifier) ~> (QualifiedIdentifier) }

  def captureIdentifier: Rule1[Identifier] = rule { capture(identifier) ~> (Identifier) }

  def identifier = rule { CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha) }

  def pack: Rule0 = rule { oneOrMore(CharPredicate.LowerAlpha) }
}
