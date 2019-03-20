package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.lang.IdentifierParser.QualifiedIdentifier
import org.parboiled2._

object ImportParser {
  case class Import(value: QualifiedIdentifier)

  def apply(input: ParserInput): Either[ParseError, Import] = {
    import Parser.DeliveryScheme.Either
    new ImportParser(input).importStatement.run()
  }
}

class ImportParser(val input: ParserInput) extends Parser {
  import ImportParser._

  def importStatement: Rule1[Import] = rule {
    (atomic("import") ~ ' ' ~ OWS ~ runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } ~ ';') ~> Import
  }

  def OWS = rule { zeroOrMore(' ') }
}
