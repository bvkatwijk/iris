package org.bvkatwijk.iris.cd

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import org.bvkatwijk.iris.cd.ConstructorDeclarationParser.Parameter
import org.bvkatwijk.iris.cd.ConstructorDeclarationParser.Constructor
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier

class ConstructorDeclarationParserTest extends FreeSpec with Matchers {

  "constructor" - {
    "single parameter" - {
      "(a: B)" in { ConstructorDeclarationParser { "(a: B)" } should be(Right(Constructor(Parameter("a", QualifiedIdentifier("B"))))) }
      "(a: C)" in { ConstructorDeclarationParser { "(a: C)" } should be(Right(Constructor(Parameter("a", QualifiedIdentifier("C"))))) }
      "(b: C)" in { ConstructorDeclarationParser { "(b: C)" } should be(Right(Constructor(Parameter("b", QualifiedIdentifier("C"))))) }
      "(name: Type)" in { ConstructorDeclarationParser { "(name: Type)" } should be(Right(Constructor(Parameter("name", QualifiedIdentifier("Type"))))) }
    }
  }
}
