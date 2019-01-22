package org.bvkatwijk.iris.md

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import org.bvkatwijk.iris.cd.ConstructorDeclarationParser.Parameter
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.md.MethodDeclarationParser.MethodDeclaration

class MethodDeclarationParserTest extends FreeSpec with Matchers {
  "method" - {
    "def a(b: C): D = {}" in {
      MethodDeclarationParser { "def a(b: C): D = {}" } should be(Right(
        MethodDeclaration(
          "a",
          Seq(Parameter("b", QualifiedIdentifier("C"))),
          QualifiedIdentifier("D"))))
    }
    "def name(value: Type): ReturnType = {}" in {
      MethodDeclarationParser { "def name(value: Type): ReturnType = {}" } should be(Right(
        MethodDeclaration(
          "name",
          Seq(Parameter("value", QualifiedIdentifier("Type"))),
          QualifiedIdentifier("ReturnType"))))
    }
    "parameter" - {
      "amount" - {
        "can be zero" in { (MethodDeclarationParser { "def a(): D = {}" }).right.get.parameters.length should be(0) }
        "can be one" in { (MethodDeclarationParser { "def a(b: C): D = {}" }).right.get.parameters.length should be(1) }
        "can be two" in { (MethodDeclarationParser { "def a(one: Type, two: Type): D = {}" }).right.get.parameters.length should be(2) }
        "can be three" in { (MethodDeclarationParser { "def a(one: Type, two: Type, three: Type): D = {}" }).right.get.parameters.length should be(3) }
      }
    }
  }
}
