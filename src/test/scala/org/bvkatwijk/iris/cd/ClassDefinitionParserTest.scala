package org.bvkatwijk.iris.cd

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.cd.ClassDefinitionParser.ClassDefinition
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier

class ClassDefinitionParserTest extends ParseTest {

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
