package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.ClassDefinition
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object ClassDefinitionParser {
  def apply(input: ParserInput): Either[CompileError, ClassDefinition] = {
    new IsolatedParser()
      .parse(new ClassDefinitionParser(input))(_.classDefinition)
  }
}

class ClassDefinitionParser(val input: ParserInput)
    extends Parser
    with Base
    with PackElementRule
    with PackRule
    with IdentifierRule
    with QualifiedIdentifierRule {
  def classDefinition: Rule1[ClassDefinition] = rule {
    atomic("class") ~ ' ' ~ qualifiedIdentifier ~ optional(
      constructorDefinition) ~ ' ' ~ '{' ~ '}' ~> (ClassDefinition)
  }

  def constructorDefinition = rule {
    '(' ~ ')'
  }
}
