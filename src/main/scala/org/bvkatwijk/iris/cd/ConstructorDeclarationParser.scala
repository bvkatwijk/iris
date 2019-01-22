package org.bvkatwijk.iris.cd

import org.parboiled2._
import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.cu.CompileError

object ConstructorDeclarationParser extends {
  case class Constructor(value: Parameter)
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

class ConstructorDeclarationParser(val input: ParserInput) extends Parser {
  import ConstructorDeclarationParser.Constructor;
  import ConstructorDeclarationParser.Parameter;
  import IdentifierParser.QualifiedIdentifier;

  def constructorDefinition: Rule1[Constructor] = rule { '(' ~ parameter ~ ')' ~> (Constructor) }

  def parameter: Rule1[Parameter] = rule { name ~ ':' ~ OWS ~ identifier ~> Parameter }

  def name: Rule1[String] = rule { capture(oneOrMore(CharPredicate.LowerAlpha)) }

  def identifier: Rule1[QualifiedIdentifier] = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }

  def OWS: Rule0 = rule { zeroOrMore(' ') }

}
