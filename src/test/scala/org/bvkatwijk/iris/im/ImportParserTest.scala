package org.bvkatwijk.iris.im

import org.scalatest.FreeSpec
import org.bvkatwijk.iris.im.ImportParser._
import org.scalatest.Matchers
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier

class ImportParserTest extends FreeSpec with Matchers {

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
