package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.lang.IdentifierParser.QualifiedIdentifier
import org.parboiled2._

object ClassDefinitionParser {
  case class ClassDefinition(qualifiedIdentifier: QualifiedIdentifier) {
    def toJava: String = {
      s"""public class ${qualifiedIdentifier.javaClassName} { @Override public String toString() { return "[${qualifiedIdentifier.javaClassName}]"; } }"""
    }
  }

  def apply(input: ParserInput): Either[CompileError, ClassDefinition] = {
    import Parser.DeliveryScheme.Either
    val parser = new ClassDefinitionParser(input)
    parser
      .classDefinition
      .run()
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class ClassDefinitionParser(val input: ParserInput) extends Parser with Base {
  import ClassDefinitionParser.ClassDefinition

  def classDefinition: Rule1[ClassDefinition] = rule { atomic("class") ~ ' ' ~ identifier ~ optional(constructorDefinition) ~ ' ' ~ '{' ~ '}' ~> (ClassDefinition) }

  def identifier = rule { runSubParser { i => new IdentifierParser(i).qualifiedIdentifier } }

  def constructorDefinition = rule { '(' ~ ')' }
}
