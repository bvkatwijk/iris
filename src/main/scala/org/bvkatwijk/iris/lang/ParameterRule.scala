package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Parameter
import org.parboiled2.{Parser, Rule1}

trait ParameterRule { self: Parser with Base with NameRule with QualifiedIdentifierRule =>
  def parameter: Rule1[Parameter] = rule {
    name ~ ':' ~ OWS ~ qualifiedIdentifier ~> Parameter
  }
  def parameters: Rule1[Seq[Parameter]] = rule {
    zeroOrMore(parameter).separatedBy(parameterSeparator)
  }
  def parameterSeparator = rule { ',' ~ OWS }
}
