package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{MethodDeclaration, Parameter}
import org.parboiled2.{Parser, Rule0, Rule1}

trait MethodRule { self: Parser with Base with NameRule with ParameterRule with QualifiedIdentifierRule =>
  def methodKeyword: Rule0 = rule { atomic("def") }

  def methodDeclaration: Rule1[MethodDeclaration] = rule {
    methodKeyword ~ ' ' ~ methodDeclarationActual ~ methodBody ~> (MethodDeclaration)
  }

  def methodDeclarationActual = rule {
    methodName ~ wrappedParameters ~ ':' ~ OWS ~ qualifiedIdentifier ~ OWS ~ '=' ~ ' '
  }

  def methodBody: Rule0 = rule { '{' ~ '}' }

  def wrappedParameters: Rule1[Seq[Parameter]] = rule { '(' ~ parameters ~ ')' }

  def methodParameter = parameter

}
