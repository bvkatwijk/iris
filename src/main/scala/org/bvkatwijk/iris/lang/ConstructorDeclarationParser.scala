package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Parameter
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object ConstructorDeclarationParser {

  case class Constructor(parameters: Seq[Parameter])

  def apply(input: ParserInput): Either[CompileError, Constructor] = {
    new IsolatedParser()
      .parse(new ConstructorDeclarationParser(input))(_.constructorDefinition)
  }
}

class ConstructorDeclarationParser(val input: ParserInput)
    extends Parser
    with Base
    with NameRule
    with PackElementRule
    with PackRule
    with IdentifierRule
    with QualifiedIdentifierRule
    with ParameterRule
    with ConstructorRule
