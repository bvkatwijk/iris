package org.bvkatwijk.iris.md

import org.parboiled2._
import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.cu.CompileError
import org.bvkatwijk.iris.cd.ConstructorDeclarationParser.Parameter

object MethodDeclarationParser {
  case class MethodDeclaration(name: String, parameters: Seq[Parameter], returnType: QualifiedIdentifier)

  def apply(input: ParserInput): Either[CompileError, MethodDeclaration] = {
    import Parser.DeliveryScheme.Either
    val parser = new MethodDeclarationParser(input)
    parser.methodDeclaration
      .run()
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class MethodDeclarationParser(val input: ParserInput) extends Parser {
  import MethodDeclarationParser.MethodDeclaration

  def methodDeclaration: Rule1[MethodDeclaration] = rule {
    atomic("def") ~ ' ' ~ name ~ '(' ~ parameters ~ ')' ~ ':' ~ OWS ~ identifier ~ OWS ~ '=' ~ ' ' ~ '{' ~ '}' ~> (MethodDeclaration)
  }

  def parameters = rule { zeroOrMore(parameter).separatedBy(',' ~ OWS) }

  def parameter: Rule1[Parameter] = rule { name ~ ':' ~ OWS ~ identifier ~> Parameter }

  def name: Rule1[String] = rule { capture(CharPredicate.LowerAlpha ~ zeroOrMore(methodNameCharacter)) }

  def identifier: Rule1[QualifiedIdentifier] = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }

  def OWS: Rule0 = rule { zeroOrMore(' ') }

  def methodNameCharacter: CharPredicate = CharPredicate.AlphaNum ++ '_'
}
