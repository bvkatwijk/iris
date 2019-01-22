package org.bvkatwijk.iris.im

import org.scalatest.FreeSpec
import org.bvkatwijk.iris.im.ImportParser._
import org.scalatest.Matchers
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier

class ImportSectionParserTest extends FreeSpec with Matchers {

  "ImportSectionParser" - {
    "on Empty" - {
      "creates empty seq" in { ImportSectionParser { "" } should be(Right(Seq())) }
    }
    "on Type" - {
      "A" in { ImportSectionParser { "import A;" } should be(Right(Seq(Import(QualifiedIdentifier("A"))))) }
      "B" in { ImportSectionParser { "import B;" } should be(Right(Seq(Import(QualifiedIdentifier("B"))))) }
      "Type" in { ImportSectionParser { "import Type;" } should be(Right(Seq(Import(QualifiedIdentifier("Type"))))) }
    }
    "on qualified Type" - {
      "a.B" in { ImportSectionParser { "import a.B;" } should be(Right(Seq(Import(QualifiedIdentifier("a.B"))))) }
      "a.C" in { ImportSectionParser { "import a.C;" } should be(Right(Seq(Import(QualifiedIdentifier("a.C"))))) }
      "b.C" in { ImportSectionParser { "import b.C;" } should be(Right(Seq(Import(QualifiedIdentifier("b.C"))))) }
      "pack.Type" in { ImportSectionParser { "import pack.Type;" } should be(Right(Seq(Import(QualifiedIdentifier("pack.Type"))))) }

      "with multiple packages" - {
        "a.b.C" in { ImportSectionParser { "import a.b.C;" } should be(Right(Seq(Import(QualifiedIdentifier("a.b.C"))))) }
      }
    }
    "multiple imports" - {
      "A; B" in { ImportSectionParser { "import A;\nimport B;" } should be(Right(Seq(Import(QualifiedIdentifier("A")), Import(QualifiedIdentifier("B"))))) }
    }
  }
}
