package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.MethodDeclaration
import org.parboiled2.{Parser, Rule0, Rule1}

trait MethodRule { self: Parser
  with Base
  with NameRule
  with ParameterRule
  with QualifiedIdentifierRule =>

  def methodKeyword: Rule0 = rule { atomic("def") }

  def methodDeclaration: Rule1[MethodDeclaration] = rule {
    methodKeyword ~ ' ' ~ methodDeclarationActual ~ methodBody ~> (MethodDeclaration)
  }

  def methodDeclarationActual = rule {
    methodName ~ wrappedParameters ~ ':' ~ OWS ~ qualifiedIdentifier ~ OWS ~ '=' ~ ' '
  }

  def wrappedMethodBody: Rule0 = rule { '{' ~ methodBody ~ '}' }

  def methodBody: Rule0 = rule { '{' ~ '}' }
}
