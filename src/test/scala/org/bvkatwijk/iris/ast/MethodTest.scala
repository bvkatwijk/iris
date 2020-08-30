package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class MethodTest extends BaseSpec {
  "Method" - {
    def subject(name: String) = MethodDeclaration(name, Seq(), QualifiedIdentifier(None, Identifier("a")))
    "name" - {
      "a" in { subject("a").name should be("a") }
    }
  }
}
