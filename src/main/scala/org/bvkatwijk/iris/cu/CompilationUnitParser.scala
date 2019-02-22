package org.bvkatwijk.iris.cu

import org.parboiled2._
import org.bvkatwijk.iris.im.ImportParser.Import
import org.bvkatwijk.iris.im.ImportParser
import org.bvkatwijk.iris.cd.ClassDefinitionParser
import org.bvkatwijk.iris.cd.ClassDefinitionParser.ClassDefinition
import org.bvkatwijk.iris.im.ImportSectionParser

object CompilationUnitParser {

  case class CompilationUnit(imports: Seq[Import] = Seq(), classes: Seq[ClassDefinition] = Seq()) {
    def toJava: String = {
      "Hello world"
    }
  }


  def apply(input: ParserInput): Either[CompileError, CompilationUnit] = {
    import Parser.DeliveryScheme.Either
    val parser = new CompilationUnitParser(input)
    parser.compilationUnit
      .run()
      .left
      .map(error => CompileError(parser.formatError(error)))
  }
}

class CompilationUnitParser(val input: ParserInput) extends Parser {
  import CompilationUnitParser._

  def compilationUnit: Rule1[CompilationUnit] = rule { importSection ~ zeroOrMore(NL) ~ classDeclarations ~ EOI ~> (CompilationUnit) }

  def importSection: Rule1[Seq[Import]] = rule { runSubParser { new ImportSectionParser(_).importSection } }

  def classDeclarations: Rule1[Seq[ClassDefinition]] = rule { zeroOrMore(classDeclaration).separatedBy(oneOrMore(NL)) }

  def classDeclaration: Rule1[ClassDefinition] = rule { runSubParser { new ClassDefinitionParser(_).classDefinition } }

  def NL: Rule0 = rule { optional('\r') ~ '\n' }

  def OWS: Rule0 = rule { zeroOrMore(' ') }

}
