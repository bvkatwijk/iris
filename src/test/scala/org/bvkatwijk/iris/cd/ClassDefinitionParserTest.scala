package org.bvkatwijk.iris.cd

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import org.bvkatwijk.iris.cd.ClassDefinitionParser.ClassDefinition
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier
import org.bvkatwijk.iris.ParseTest

class ClassDefinitionParserTest extends FreeSpec with Matchers {

  "simple type" - {
    "type A" in { ClassDefinitionParser { "class A {}" } should be(Right(ClassDefinition(QualifiedIdentifier("A")))) }
    "type B" in { ClassDefinitionParser { "class B {}" } should be(Right(ClassDefinition(QualifiedIdentifier("B")))) }
    "type Type" in { ClassDefinitionParser { "class Type {}" } should be(Right(ClassDefinition(QualifiedIdentifier("Type")))) }
  }

  "with constructor" - {
    "empty" - {
      "A()" in { ClassDefinitionParser { "class A() {}" } should be(Right(ClassDefinition(QualifiedIdentifier("A")))) }
      "B()" in { ClassDefinitionParser { "class B() {}" } should be(Right(ClassDefinition(QualifiedIdentifier("B")))) }
    }
  }
}
