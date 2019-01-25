package org.bvkatwijk.iris.im

import scala.collection.immutable
import org.parboiled2._
import org.parboiled2.CharPredicate
import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.cu.CompileError

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
