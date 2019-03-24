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

class MethodDeclarationParser(val input: ParserInput) extends Parser
  with Base
  with NameRule
  with PackElementRule
  with PackRule
  with IdentifierRule
  with QualifiedIdentifierRule
  with ParameterRule
{
  import MethodDeclarationParser.MethodDeclaration

  def methodDeclaration: Rule1[MethodDeclaration] = rule {
    methodKeyword ~ ' ' ~ methodDeclarationActual ~ methodBody ~> (MethodDeclaration)
  }

  def methodDeclarationActual = rule {
    methodName ~ wrappedParameters ~ ':' ~ OWS ~ qualifiedIdentifier ~ OWS ~ '=' ~ ' '
  }

  def methodKeyword: Rule0 = rule { atomic("def") }

  def methodBody: Rule0 = rule { '{' ~ '}' }

  def wrappedParameters: Rule1[Seq[Parameter]] = rule { '(' ~ parameters ~ ')' }
}
