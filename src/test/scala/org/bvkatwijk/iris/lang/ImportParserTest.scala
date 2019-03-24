package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.Import

class ImportParserTest extends ParseTest {
  "ImportParser" - {
    "on Type" - {
      "A" in { ImportParser { "import A;" } should be(Right(Import(qualifiedIdentifier("A")))) }
      "B" in { ImportParser { "import B;" } should be(Right(Import(qualifiedIdentifier("B")))) }
      "Type" in { ImportParser { "import Type;" } should be(Right(Import(qualifiedIdentifier("Type")))) }

      "a" in { ImportParser { "import a;" } should be('left) }
    }
    "on qualified Type" - {
      "a.B" in { ImportParser { "import a.B;" } should be(Right(Import(onePack("a", "B")))) }
      "a.C" in { ImportParser { "import a.C;" } should be(Right(Import(onePack("a", "C")))) }
      "b.C" in { ImportParser { "import b.C;" } should be(Right(Import(onePack("b", "C")))) }
      "pack.Type" in { ImportParser { "import pack.Type;" } should be(Right(Import(onePack("pack", "Type")))) }

      "with multiple packages" - {
        "a.b.C" in { ImportParser { "import a.b.C;" } should be(Right(Import(twoPack("a", "b", "C")))) }
      }
    }
    "syntax" - {
      "needs import keyword" in { ImportParser { " A;" } should be('left) }
      "needs space after import" in { ImportParser { "importA;" } should be('left) }
    }
  }
}