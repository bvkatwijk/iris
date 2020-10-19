package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Parameter, QualifiedIdentifier}
import org.parboiled2.{Parser, Rule1}

trait ParameterRule {
  self: Parser with Base with NameRule with QualifiedIdentifierRule =>
  def parameter: Rule1[Parameter] = rule {
    name ~ ':' ~ OWS ~ qualifiedIdentifier ~> (
        (name: String,
         typeValue: QualifiedIdentifier) => Parameter(name, typeValue)
    )
  }

  def parameters: Rule1[Seq[Parameter]] = rule {
    zeroOrMore(parameter).separatedBy(parameterSeparator)
  }

  def wrappedParameters: Rule1[Seq[Parameter]] = rule {
    '(' ~ parameters ~ ')'
  }

  def parameterSeparator = rule {
    ',' ~ OWS
  }
}
