package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class QualifiedIdentifierSpec extends BaseSpec {
  "asFull" - {
    "a.B" in {
      QualifiedIdentifier(Some(Pack(Seq(PackageElement("a")))), Identifier("B")).asFull shouldBe "a.B"
    }
  }
}
