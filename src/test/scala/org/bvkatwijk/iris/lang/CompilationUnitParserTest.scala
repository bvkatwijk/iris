package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.{ClassDefinition, Import}
import org.bvkatwijk.iris.lang.CompilationUnitParser.CompilationUnit

class CompilationUnitParserTest extends ParseTest {

  "empty" in {
    CompilationUnitParser {
      ""
    } should be(Right(new CompilationUnit(Seq())))
  }

  "with Imports" - {
    "A" in {
      CompilationUnitParser {
        "import A;"
      } should be(
        Right(new CompilationUnit(Seq(Import(qualifiedIdentifier("A"))))))
    }
    "A; B" in {
      CompilationUnitParser {
        "import A;\nimport B;"
      } should be(
        Right(new CompilationUnit(Seq(Import(qualifiedIdentifier("A")),
                                      Import(qualifiedIdentifier("B"))))))
    }
    "a.A" in {
      CompilationUnitParser {
        "import a.A;"
      } should be(Right(new CompilationUnit(Seq(Import(onePack("a", "A"))))))
    }
  }

  "class definition" - {
    "single A" in {
      CompilationUnitParser {
        "class A {}"
      } should be(
        Right(
          new CompilationUnit(Seq(),
                              Seq(ClassDefinition(qualifiedIdentifier("A"))))))
    }
    "single B" in {
      CompilationUnitParser {
        "class B {}"
      } should be(
        Right(
          new CompilationUnit(Seq(),
                              Seq(ClassDefinition(qualifiedIdentifier("B"))))))
    }
    "single Type" in {
      CompilationUnitParser {
        "class Type {}"
      } should be(Right(
        new CompilationUnit(Seq(),
                            Seq(ClassDefinition(qualifiedIdentifier("Type"))))))
    }
  }

  "imports and class definition" - {
    "import A, class B" in {
      CompilationUnitParser {
        "import A;\n\nclass B {}"
      } should be(
        Right(
          new CompilationUnit(Seq(Import(qualifiedIdentifier("A"))),
                              Seq(ClassDefinition(qualifiedIdentifier("B"))))))
    }
    "import A and B, class C" in {
      CompilationUnitParser {
        "import A;\nimport B;\n\nclass C {}"
      } should be(
        Right(
          new CompilationUnit(Seq(Import(qualifiedIdentifier("A")),
                                  Import(qualifiedIdentifier("B"))),
                              Seq(ClassDefinition(qualifiedIdentifier("C"))))))
    }
    "import A and B, class C and D" in {
      CompilationUnitParser {
        "import A;\nimport B;\n\nclass C {}\n\nclass D {}"
      } should be(
        Right(new CompilationUnit(
          Seq(Import(qualifiedIdentifier("A")),
              Import(qualifiedIdentifier("B"))),
          Seq(ClassDefinition(qualifiedIdentifier("C")),
              ClassDefinition(qualifiedIdentifier("D")))
        )))
    }
  }

}
