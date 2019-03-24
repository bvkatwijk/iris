package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.Parameter
import org.bvkatwijk.iris.lang.ConstructorDeclarationParser.Constructor

class ConstructorDeclarationParserTest extends ParseTest {
  "constructor" - {
    "single parameter" - {
      "(a: B)" in { ConstructorDeclarationParser { "(a: B)" } should be(Right(Constructor(Seq(Parameter("a", qualifiedIdentifier("B")))))) }
      "(a: C)" in { ConstructorDeclarationParser { "(a: C)" } should be(Right(Constructor(Seq(Parameter("a", qualifiedIdentifier("C")))))) }
      "(b: C)" in { ConstructorDeclarationParser { "(b: C)" } should be(Right(Constructor(Seq(Parameter("b", qualifiedIdentifier("C")))))) }
      "(name: Type)" in { ConstructorDeclarationParser { "(name: Type)" } should be(Right(Constructor(Seq(Parameter("name", qualifiedIdentifier("Type")))))) }
    }
    "multiple parameters" - {
      "(a: B, c: D)" in {
        ConstructorDeclarationParser { "(a: B, c: D)" } should be(Right(Constructor(Seq(
          Parameter("a", qualifiedIdentifier("B")),
          Parameter("c", qualifiedIdentifier("D"))))))
      }
      "(one: Type, other: OtherType)" in {
        ConstructorDeclarationParser { "(one: Type, other: OtherType)" } should be(Right(Constructor(Seq(
          Parameter("one", qualifiedIdentifier("Type")),
          Parameter("other", qualifiedIdentifier("OtherType"))))))
      }
    }
  }
}
