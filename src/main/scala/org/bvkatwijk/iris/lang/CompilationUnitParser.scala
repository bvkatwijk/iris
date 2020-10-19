package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.ClassDefinition
import org.bvkatwijk.iris.ast.Import
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2._

object CompilationUnitParser {

  case class CompilationUnit(imports: Seq[Import] = Seq(),
                             classes: Seq[ClassDefinition] = Seq()) {
    def toJava: String =
      "Hello world"
  }

  def apply(input: ParserInput): Either[CompileError, CompilationUnit] =
    new IsolatedParser()
      .parse(new CompilationUnitParser(input))(_.compilationUnit)
}

class CompilationUnitParser(val input: ParserInput)
    extends Parser
    with Base
    with PackElementRule
    with PackRule
    with IdentifierRule
    with QualifiedIdentifierRule
    with ImportRule {

  import CompilationUnitParser._

  def compilationUnit: Rule1[CompilationUnit] = rule {
    importSection ~ zeroOrMore(NL) ~ classDeclarations ~ EOI ~> (CompilationUnit)
  }

  def classDeclarations: Rule1[Seq[ClassDefinition]] = rule {
    zeroOrMore(classDeclaration).separatedBy(oneOrMore(NL))
  }

  def classDeclaration: Rule1[ClassDefinition] = rule {
    runSubParser {
      new ClassDefinitionParser(_).classDefinition
    }
  }
}
