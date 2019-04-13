package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.lang.ConstructorDeclarationParser.Constructor
import org.parboiled2.{Parser, Rule1}

trait ConstructorRule { self: Parser
  with Base
  with NameRule
  with PackElementRule
  with PackRule
  with IdentifierRule
  with QualifiedIdentifierRule
  with ParameterRule =>

  def constructorDefinition: Rule1[Constructor] = rule {
    '(' ~ oneOrMore(parameter).separatedBy(',' ~ OWS) ~ ')' ~> (Constructor)
  }

}
