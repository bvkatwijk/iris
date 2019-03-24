package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Parameter, QualifiedIdentifier}
import org.bvkatwijk.iris.lang.ConstructorDeclarationParser.Constructor
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object ConstructorDeclarationParser {
  case class Constructor(parameters: Seq[Parameter])

  def apply(input: ParserInput): Either[CompileError, Constructor] = {
    new IsolatedParser().parse(new ConstructorDeclarationParser(input))(_.constructorDefinition)
  }
}

class ConstructorDeclarationParser(val input: ParserInput)
  extends Parser
  with Base
{

  def constructorDefinition: Rule1[Constructor] = rule { '(' ~ oneOrMore(parameter).separatedBy(',' ~ OWS) ~ ')' ~> (Constructor) }

  def parameter: Rule1[Parameter] = rule { name ~ ':' ~ OWS ~ identifier ~> Parameter }

  def name: Rule1[String] = rule { capture(oneOrMore(CharPredicate.LowerAlpha)) }

  def identifier: Rule1[QualifiedIdentifier] = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }
}
