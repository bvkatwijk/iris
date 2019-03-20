package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.lang.ImportParser.Import
import org.parboiled2._

object ImportSectionParser {

  def apply(input: ParserInput): Either[ParseError, Seq[Import]] = {
    import Parser.DeliveryScheme.Either

    new ImportSectionParser(input)
      .importSection
      .run()
  }

}

class ImportSectionParser(val input: ParserInput) extends Parser with Base {

  def importSection: Rule1[Seq[Import]] = rule { zeroOrMore(importStatement2).separatedBy(NL) }

  def importStatement2: Rule1[Import] = rule { runSubParser { i => new ImportParser(i).importStatement } }

  def NL: Rule0 = rule { optional('\r') ~ '\n' }

}
