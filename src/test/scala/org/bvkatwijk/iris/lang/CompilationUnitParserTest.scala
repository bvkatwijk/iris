package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.lang.ClassDefinitionParser.ClassDefinition
import org.bvkatwijk.iris.lang.CompilationUnitParser.CompilationUnit
import org.bvkatwijk.iris.lang.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.lang.ImportParser.Import

class CompilationUnitParserTest extends ParseTest {

  "empty" in { CompilationUnitParser { "" } should be(Right(new CompilationUnit(Seq()))) }

  "with Imports" - {
    "A" in {
      CompilationUnitParser { "import A;" } should be(Right(new CompilationUnit(
        Seq(Import(QualifiedIdentifier("A"))))))
    }
    "A; B" in {
      CompilationUnitParser { "import A;\nimport B;" } should be(Right(new CompilationUnit(
        Seq(Import(QualifiedIdentifier("A")), Import(QualifiedIdentifier("B"))))))
    }
    "a.A" in {
      CompilationUnitParser { "import a.A;" } should be(Right(new CompilationUnit(
        Seq(Import(QualifiedIdentifier("a.A"))))))
    }
  }

  "class definition" - {
    "single A" in {
      CompilationUnitParser { "class A {}" } should be(Right(new CompilationUnit(
        Seq(),
        Seq(ClassDefinition(QualifiedIdentifier("A"))))))
    }
    "single B" in {
      CompilationUnitParser { "class B {}" } should be(Right(new CompilationUnit(
        Seq(),
        Seq(ClassDefinition(QualifiedIdentifier("B"))))))
    }
    "single Type" in {
      CompilationUnitParser { "class Type {}" } should be(Right(new CompilationUnit(
        Seq(),
        Seq(ClassDefinition(QualifiedIdentifier("Type"))))))
    }
  }

  "imports and class definition" - {
    "import A, class B" in {
      CompilationUnitParser { "import A;\n\nclass B {}" } should be(Right(new CompilationUnit(
        Seq(Import(QualifiedIdentifier("A"))),
        Seq(ClassDefinition(QualifiedIdentifier("B"))))))
    }
    "import A and B, class C" in {
      CompilationUnitParser { "import A;\nimport B;\n\nclass C {}" } should be(Right(new CompilationUnit(
        Seq(
          Import(QualifiedIdentifier("A")),
          Import(QualifiedIdentifier("B"))),
        Seq(
          ClassDefinition(QualifiedIdentifier("C"))))))
    }
    "import A and B, class C and D" in {
      CompilationUnitParser { "import A;\nimport B;\n\nclass C {}\n\nclass D {}" } should be(Right(new CompilationUnit(
        Seq(
          Import(QualifiedIdentifier("A")),
          Import(QualifiedIdentifier("B"))),
        Seq(
          ClassDefinition(QualifiedIdentifier("C")),
          ClassDefinition(QualifiedIdentifier("D"))))))
    }
  }

}
