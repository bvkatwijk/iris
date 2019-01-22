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
  }
}
