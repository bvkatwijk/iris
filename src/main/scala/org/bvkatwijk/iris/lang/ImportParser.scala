package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Import
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object ImportParser {
  def apply(input: ParserInput): Either[CompileError, Import] = {
    new IsolatedParser().parse(new ImportParser(input))(_.importStatement)
  }
}

class ImportParser(val input: ParserInput) extends Parser
  with Base
  with PackElementRule
  with PackRule
  with IdentifierRule
  with QualifiedIdentifierRule
{
  def importStatement: Rule1[Import] = rule {
    (atomic("import") ~ ' ' ~ OWS ~ qualifiedIdentifier ~ ';') ~> Import
  }
}
