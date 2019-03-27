package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.Import

class ImportParserTest extends ParseTest {
  "ImportParser" - {
    def actual(value: String) = ImportParser { value }
    def deny(value: String) =  actual(value) should be('left)
    "on Type" - {
      "unqualified" - {
        def withType(value: String) = actual(s"import $value;") should be(Right(Import(qualifiedIdentifier(value))))
        "A" in withType("A")
        "B" in withType("B")
        "Type" in withType("Type")

        "denies" -{
          "a" in deny("import a;")
          "-" in deny("import -;")
        }
      }
      "qualified" - {

        "a.B" in { ImportParser { "import a.B;" } should be(Right(Import(onePack("a", "B")))) }
        "a.C" in { ImportParser { "import a.C;" } should be(Right(Import(onePack("a", "C")))) }
        "b.C" in { ImportParser { "import b.C;" } should be(Right(Import(onePack("b", "C")))) }
        "pack.Type" in { ImportParser { "import pack.Type;" } should be(Right(Import(onePack("pack", "Type")))) }

        "with multiple packages" - {
          "a.b.C" in { ImportParser { "import a.b.C;" } should be(Right(Import(twoPack("a", "b", "C")))) }
        }
      }
    }
    "syntax" - {
      "needs a Type" in deny("import ;")
      "needs import keyword" in deny(" A;")
      "needs space after import" in deny("importA;")
    }
  }
}
