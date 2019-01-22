package org.bvkatwijk.iris.id;

import scala.collection.immutable
import org.parboiled2._
import org.bvkatwijk.iris.cu.CompileError

object IdentifierParser extends {
  case class QualifiedIdentifier(value: String)

  def apply(input: ParserInput): Either[ParseError, QualifiedIdentifier] = {
    import Parser.DeliveryScheme.Either
    new IdentifierParser(input).qualifiedIdentifier.run()
  }
}

class IdentifierParser(val input: ParserInput) extends Parser {
  import IdentifierParser.QualifiedIdentifier

  def qualifiedIdentifier: Rule1[QualifiedIdentifier] = rule { capture(zeroOrMore(pack ~ '.') ~ identifier) ~> QualifiedIdentifier }

  def identifier = rule { CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha) }

  def pack: Rule0 = rule { oneOrMore(CharPredicate.LowerAlpha) }

  def OWS: Rule0 = rule { zeroOrMore(' ') }

}
