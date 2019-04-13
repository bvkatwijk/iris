package org.bvkatwijk.iris.ast

import org.scalatest.{FreeSpec, Matchers}

class MethodTest extends FreeSpec with Matchers {
  "Method" - {
    def subject(name: String) = MethodDeclaration(name, Seq(), QualifiedIdentifier(None, Identifier("a")))
    "name" - {
      "a" in { subject("a").name should be("a") }
    }
  }
}
