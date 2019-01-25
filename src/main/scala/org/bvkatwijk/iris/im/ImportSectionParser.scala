package org.bvkatwijk.iris.im

import scala.collection.immutable
import org.parboiled2._
import org.parboiled2.CharPredicate
import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.im.ImportParser.Import
import org.bvkatwijk.iris.cu.CompileError

object ImportSectionParser {

  def apply(input: ParserInput): Either[ParseError, Seq[Import]] = {
    import Parser.DeliveryScheme.Either

    new ImportSectionParser(input)
      .importSection
      .run()
  }

}

class ImportSectionParser(val input: ParserInput) extends Parser {

  def importSection: Rule1[Seq[Import]] = rule { zeroOrMore(importStatement2).separatedBy(NL) }

  def importStatement2: Rule1[Import] = rule { runSubParser { i => new ImportParser(i).importStatement } }

  def NL: Rule0 = rule { optional('\r') ~ '\n' }

  def OWS: Rule0 = rule { zeroOrMore(' ') }

}
