package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class PackTest extends BaseSpec {
  "asStatement" - {
    "a" in {
      Pack.fromString("a").asStatement() should be("package a;")
    }
    "b" in {
      Pack.fromString("b").asStatement() should be("package b;")
    }
    "a.b" in {
      Pack(Seq(PackageElement("a"), PackageElement("b")))
        .asStatement() should be("package a.b;")
    }
  }

  "asQualification" - {
    "a" in {
      Pack.fromString("a").asQualification() should be("a")
    }
    "b" in {
      Pack.fromString("b").asQualification() should be("b")
    }
    "a.b" in {
      Pack(Seq(PackageElement("a"), PackageElement("b")))
        .asQualification() should be("a.b")
    }
  }

  ".fromString" - {
    "a" in {
      Pack.fromString("a") should be(Pack(Seq(PackageElement("a"))))
    }
    "b" in {
      Pack.fromString("b") should be(Pack(Seq(PackageElement("b"))))
    }
    "a.b" in {
      Pack.fromString("a.b") should be(
        Pack(Seq(PackageElement("a"), PackageElement("b")))
      )
    }
  }
}
