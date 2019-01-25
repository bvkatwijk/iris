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
    "name" - {
      "can be lowercase" in { (MethodDeclarationParser { "def a(): B = {}" }).right.get.name should be("a") }
      "can be long" in { (MethodDeclarationParser { "def veryveryveryveryveryvery(): B = {}" }).right.get.name should be("veryveryveryveryveryvery") }
      "can be mixedCase" in { (MethodDeclarationParser { "def mixedCase(): B = {}" }).right.get.name should be("mixedCase") }
      "can contain numbers" in { (MethodDeclarationParser { "def contains1Number(): B = {}" }).right.get.name should be("contains1Number") }
      "can't start with uppercase letter" in { MethodDeclarationParser { "def Startwithuppercase(): B = {}" } should be('left) }
      "can't start with number" in { MethodDeclarationParser { "def 1StartwithNumber(): B = {}" } should be('left) }
    }
    "parameter" - {
      "amount" - {
        "can be zero" in { (MethodDeclarationParser { "def a(): D = {}" }).right.get.parameters.length should be(0) }
        "can be one" in { (MethodDeclarationParser { "def a(one: Type): D = {}" }).right.get.parameters.length should be(1) }
        "can be two" in { (MethodDeclarationParser { "def a(one: Type, two: Type): D = {}" }).right.get.parameters.length should be(2) }
        "can be three" in { (MethodDeclarationParser { "def a(one: Type, two: Type, three: Type): D = {}" }).right.get.parameters.length should be(3) }
      }
    }
  }
}
