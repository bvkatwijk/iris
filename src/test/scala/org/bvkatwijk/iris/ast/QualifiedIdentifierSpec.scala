package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class QualifiedIdentifierSpec extends BaseSpec {
  "asFull" - {
    "no package" - {
      def noPackage(name: String) =
        QualifiedIdentifier.unqualified(Identifier(name)).asFull should be(name)

      "A" in noPackage("A")
      "B" in noPackage("B")
      "Type" in noPackage("Type")
    }

    "one package" - {
      def onePackage(pack: String, identifier: String) =
        QualifiedIdentifier(
          Some(Pack(Seq(PackageElement(pack)))),
          Identifier(identifier)
        ).asFull should be(pack + "." + identifier)

      "a.B" in onePackage("a", "B")
      "a.C" in onePackage("a", "C")
      "b.C" in onePackage("b", "C")
      "pack.Type" in onePackage("pack", "Type")
    }

    "two packages" - {
      def twoPackages(firstPackageElement: String,
                      secondPackageElement: String,
                      identifier: String) =
        QualifiedIdentifier(
          Some(
            Pack(
              Seq(PackageElement(firstPackageElement),
                  PackageElement(secondPackageElement))
            )
          ),
          Identifier(identifier)
        ).asFull should be(
          firstPackageElement + "." + secondPackageElement + "." + identifier
        )

      "a.b.C" in twoPackages("a", "b", "C")
      "a.b.D" in twoPackages("a", "b", "D")
      "b.c.D" in twoPackages("b", "c", "D")
      "one.two.Type" in twoPackages("one", "two", "Type")
    }
  }
}
