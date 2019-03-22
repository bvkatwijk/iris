package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.ClassDefinition
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object ClassDefinitionParser {
  def apply(input: ParserInput): Either[CompileError, ClassDefinition] = {
    new IsolatedParser().parse(new ClassDefinitionParser(input))(_.classDefinition)
  }
}

class ClassDefinitionParser(val input: ParserInput) extends Parser with Base {
  def classDefinition: Rule1[ClassDefinition] = rule { atomic("class") ~ ' ' ~ identifier ~ optional(constructorDefinition) ~ ' ' ~ '{' ~ '}' ~> (ClassDefinition) }
  def identifier = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }
  def constructorDefinition = rule { '(' ~ ')' }
}
