package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Identifier, QualifiedIdentifier}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object IdentifierParser {
  def apply(input: ParserInput): Either[CompileError, QualifiedIdentifier] = {
    new IsolatedParser().parse(new IdentifierParser(input))(_.qualifiedIdentifier)
  }

  def identifier(input: ParserInput): Either[CompileError, Identifier] = {
    new IsolatedParser().parse(new IdentifierParser(input))(_.identifier)
  }
}

class IdentifierParser(val input: ParserInput) extends Parser
  with Base
  with IdentifierRule
  with QualifiedIdentifierRule
  with PackElementRule
  with PackRule
