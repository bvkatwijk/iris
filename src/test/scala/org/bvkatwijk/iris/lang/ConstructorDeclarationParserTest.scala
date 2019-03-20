package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.QualifiedIdentifier
import org.bvkatwijk.iris.lang.ConstructorDeclarationParser.{Constructor, Parameter}

class ConstructorDeclarationParserTest extends ParseTest {
  "constructor" - {
    "single parameter" - {
      "(a: B)" in { ConstructorDeclarationParser { "(a: B)" } should be(Right(Constructor(Seq(Parameter("a", QualifiedIdentifier("B")))))) }
      "(a: C)" in { ConstructorDeclarationParser { "(a: C)" } should be(Right(Constructor(Seq(Parameter("a", QualifiedIdentifier("C")))))) }
      "(b: C)" in { ConstructorDeclarationParser { "(b: C)" } should be(Right(Constructor(Seq(Parameter("b", QualifiedIdentifier("C")))))) }
      "(name: Type)" in { ConstructorDeclarationParser { "(name: Type)" } should be(Right(Constructor(Seq(Parameter("name", QualifiedIdentifier("Type")))))) }
    }
    "multiple parameters" - {
      "(a: B, c: D)" in {
        ConstructorDeclarationParser { "(a: B, c: D)" } should be(Right(Constructor(Seq(
          Parameter("a", QualifiedIdentifier("B")),
          Parameter("c", QualifiedIdentifier("D"))))))
      }
      "(one: Type, other: OtherType)" in {
        ConstructorDeclarationParser { "(one: Type, other: OtherType)" } should be(Right(Constructor(Seq(
          Parameter("one", QualifiedIdentifier("Type")),
          Parameter("other", QualifiedIdentifier("OtherType"))))))
      }
    }
  }
}
