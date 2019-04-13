package org.bvkatwijk.iris.ast

import org.scalatest.{FreeSpec, Matchers}

class PackTest extends FreeSpec with Matchers {
  "Pack" - {
    "asStatement" - {
      "a" in { Pack(Seq(PackageElement("a"))).asStatement() should be("package a;") }
      "b" in { Pack(Seq(PackageElement("b"))).asStatement() should be("package b;") }
      "a.b" in { Pack(Seq(PackageElement("a"), PackageElement("b"))).asStatement() should be("package a.b;") }
    }
  }

}
