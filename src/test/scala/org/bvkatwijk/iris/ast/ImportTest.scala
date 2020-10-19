package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class ImportTest extends BaseSpec {
  "Import" - {
    "asStatement" - {
      "A" in {
        Import(QualifiedIdentifier.unqualified(Identifier("A"))).asStatement should be(
          "import A;"
        )
      }
      "B" in {
        Import(QualifiedIdentifier.unqualified(Identifier("B"))).asStatement should be(
          "import B;"
        )
      }
      "a.B" in {
        Import(
          QualifiedIdentifier(Option(Pack(Seq(PackageElement("a")))),
                              Identifier("B"))
        ).asStatement should be("import a.B;")
      }
      "a.C" in {
        Import(
          QualifiedIdentifier(Option(Pack(Seq(PackageElement("a")))),
                              Identifier("C"))
        ).asStatement should be("import a.C;")
      }
      "b.C" in {
        Import(
          QualifiedIdentifier(Option(Pack(Seq(PackageElement("b")))),
                              Identifier("C"))
        ).asStatement should be("import b.C;")
      }
    }
  }
}
