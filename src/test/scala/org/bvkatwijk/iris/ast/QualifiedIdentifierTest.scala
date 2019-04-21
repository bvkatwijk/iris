package org.bvkatwijk.iris.ast

import org.scalatest.{FreeSpec, Matchers}

class QualifiedIdentifierTest extends FreeSpec with Matchers {
  "QualifiedIdentifier" - {
    "asFull" - {
      "no package" - {
        def noPackage(name: String) = QualifiedIdentifier.unqualified(Identifier(name)).asFull should be(name)
        "A" in noPackage("A")
        "B" in noPackage("B")
        "Type" in noPackage("Type")
      }
      "one package" - {
        def onePackage(pack: String, identifier: String) = {
          QualifiedIdentifier(
            Some(Pack(Seq(PackageElement(pack)))),
            Identifier(identifier)
          ).asFull should be(pack + "." + identifier)
        }
        "a.B" in onePackage("a", "B")
        "a.C" in onePackage("a", "C")
        "b.C" in onePackage("b", "C")
        "pack.Type" in onePackage("pack", "Type")
      }
    }
  }

}
