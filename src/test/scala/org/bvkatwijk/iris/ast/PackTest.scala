package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class PackTest extends BaseSpec {
  "Pack" - {
    "asStatement" - {
      "a" in { Pack(Seq(PackageElement("a"))).asStatement() should be("package a;") }
      "b" in { Pack(Seq(PackageElement("b"))).asStatement() should be("package b;") }
      "a.b" in { Pack(Seq(PackageElement("a"), PackageElement("b"))).asStatement() should be("package a.b;") }
    }
  }

}
