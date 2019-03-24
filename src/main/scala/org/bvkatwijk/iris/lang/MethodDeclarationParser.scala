package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Parameter, QualifiedIdentifier}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object MethodDeclarationParser {
  case class MethodDeclaration(name: String, parameters: Seq[Parameter], returnType: QualifiedIdentifier)

  def apply(input: ParserInput): Either[CompileError, MethodDeclaration] = {
    new IsolatedParser().parse(new MethodDeclarationParser(input))(_.methodDeclaration)
  }
}

class MethodDeclarationParser(val input: ParserInput) extends Parser with Base {
  import MethodDeclarationParser.MethodDeclaration

  def methodDeclaration: Rule1[MethodDeclaration] = rule { methodKeyword ~ ' ' ~ methodDeclarationActual ~ methodBody ~> (MethodDeclaration) }

  def methodDeclarationActual = rule { name ~ wrappedParameters ~ ':' ~ OWS ~ identifier ~ OWS ~ '=' ~ ' ' }

  def methodKeyword: Rule0 = rule { atomic("def") }

  def methodBody: Rule0 = rule { '{' ~ '}' }

  def wrappedParameters: Rule1[Seq[Parameter]] = rule { '(' ~ parameters ~ ')' }

  def parameters: Rule1[Seq[Parameter]] = rule { zeroOrMore(parameter).separatedBy(',' ~ OWS) }

  def parameter: Rule1[Parameter] = rule { name ~ ':' ~ OWS ~ identifier ~> Parameter }

  def name: Rule1[String] = rule { capture(CharPredicate.LowerAlpha ~ zeroOrMore(methodNameCharacter)) }

  def identifier: Rule1[QualifiedIdentifier] = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }

  def methodNameCharacter: CharPredicate = CharPredicate.AlphaNum ++ '_'
}
