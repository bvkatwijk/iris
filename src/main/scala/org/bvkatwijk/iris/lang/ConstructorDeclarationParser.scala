package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.lang.IdentifierParser.QualifiedIdentifier
import org.parboiled2._

object ConstructorDeclarationParser {
  case class Constructor(parameters: Seq[Parameter])
  case class Parameter(name: String, typeValue: QualifiedIdentifier)

  def apply(input: ParserInput): Either[CompileError, Constructor] = {
    import Parser.DeliveryScheme.Either
    val parser = new ConstructorDeclarationParser(input)
    parser.constructorDefinition
      .run()
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class ConstructorDeclarationParser(val input: ParserInput) extends Parser with Base {
  import ConstructorDeclarationParser.{Constructor, Parameter}
  import IdentifierParser.QualifiedIdentifier;

  def constructorDefinition: Rule1[Constructor] = rule { '(' ~ oneOrMore(parameter).separatedBy(',' ~ OWS) ~ ')' ~> (Constructor) }

  def parameter: Rule1[Parameter] = rule { name ~ ':' ~ OWS ~ identifier ~> Parameter }

  def name: Rule1[String] = rule { capture(oneOrMore(CharPredicate.LowerAlpha)) }

  def identifier: Rule1[QualifiedIdentifier] = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }
}
