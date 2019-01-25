package org.bvkatwijk.iris.id;

import org.bvkatwijk.iris.cu.CompileError
import org.parboiled2._

object IdentifierParser {
  case class QualifiedIdentifier(value: String)
  case class Identifier(value: String)

  def apply(input: ParserInput): Either[CompileError, QualifiedIdentifier] = parse(input, _.qualifiedIdentifier)

  def identifier(input: ParserInput): Either[CompileError, Identifier] = parse(input, _.captureIdentifier)

  def parse[T](input: ParserInput, ruleSupplier: IdentifierParser => Rule1[T]): Either[CompileError, T] = {
    import Parser.DeliveryScheme.Either
    val parser = new IdentifierParser(input)
    parser.__run(ruleSupplier(parser))
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class IdentifierParser(val input: ParserInput) extends Parser {
  import IdentifierParser.Identifier
  import IdentifierParser.QualifiedIdentifier

  def qualifiedIdentifier: Rule1[QualifiedIdentifier] = rule { capture(zeroOrMore(pack ~ '.') ~ identifier) ~> (QualifiedIdentifier) }

  def captureIdentifier: Rule1[Identifier] = rule { capture(identifier) ~> (Identifier) }

  def identifier = rule { CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha) }

  def pack: Rule0 = rule { oneOrMore(CharPredicate.LowerAlpha) }

  def OWS: Rule0 = rule { zeroOrMore(' ') }
}
