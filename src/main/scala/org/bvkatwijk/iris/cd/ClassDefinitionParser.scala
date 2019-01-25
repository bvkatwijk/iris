package org.bvkatwijk.iris.cd

import org.parboiled2._
import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.cu.CompileError

object ClassDefinitionParser {
  case class ClassDefinition(value: QualifiedIdentifier)

  def apply(input: ParserInput): Either[ParseError, ClassDefinition] = {
    import Parser.DeliveryScheme.Either
    new ClassDefinitionParser(input).classDefinition.run()
  }
}

class ClassDefinitionParser(val input: ParserInput) extends Parser {
  import ClassDefinitionParser.ClassDefinition

  def classDefinition: Rule1[ClassDefinition] = rule { atomic("class") ~ ' ' ~ identifier ~ optional(constructorDefinition) ~ ' ' ~ '{' ~ '}' ~> (ClassDefinition) }

  def identifier = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }

  def constructorDefinition = rule { '(' ~ ')' }

  def OWS: Rule0 = rule { zeroOrMore(' ') }
}
