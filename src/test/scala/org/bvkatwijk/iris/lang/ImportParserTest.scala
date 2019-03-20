package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.QualifiedIdentifier
import org.bvkatwijk.iris.lang.ImportParser.Import

class ImportParserTest extends ParseTest {
  "ImportParser" - {
    "on Type" - {
      "A" in { ImportParser { "import A;" } should be(Right(Import(QualifiedIdentifier("A")))) }
      "B" in { ImportParser { "import B;" } should be(Right(Import(QualifiedIdentifier("B")))) }
      "Type" in { ImportParser { "import Type;" } should be(Right(Import(QualifiedIdentifier("Type")))) }

      "a" in { ImportParser { "import a;" } should be('left) }
    }
    "on qualified Type" - {
      "a.B" in { ImportParser { "import a.B;" } should be(Right(Import(QualifiedIdentifier("a.B")))) }
      "a.C" in { ImportParser { "import a.C;" } should be(Right(Import(QualifiedIdentifier("a.C")))) }
      "b.C" in { ImportParser { "import b.C;" } should be(Right(Import(QualifiedIdentifier("b.C")))) }
      "pack.Type" in { ImportParser { "import pack.Type;" } should be(Right(Import(QualifiedIdentifier("pack.Type")))) }

      "with multiple packages" - {
        "a.b.C" in { ImportParser { "import a.b.C;" } should be(Right(Import(QualifiedIdentifier("a.b.C")))) }
      }
    }
    "syntax" - {
      "needs import keyword" in { ImportParser { " A;" } should be('left) }
      "needs space after import" in { ImportParser { "importA;" } should be('left) }
    }
  }
}
