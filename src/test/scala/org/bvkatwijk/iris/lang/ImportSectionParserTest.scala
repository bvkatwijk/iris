package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.Import

class ImportSectionParserTest extends ParseTest {
  "ImportSectionParser" - {
    "on Empty" - {
      "creates empty seq" in { ImportSectionParser { "" } should be(Right(Seq())) }
    }
    "on Type" - {
      "A" in { ImportSectionParser { "import A;" } should be(Right(Seq(Import(qualifiedIdentifier("A"))))) }
      "B" in { ImportSectionParser { "import B;" } should be(Right(Seq(Import(qualifiedIdentifier("B"))))) }
      "Type" in { ImportSectionParser { "import Type;" } should be(Right(Seq(Import(qualifiedIdentifier("Type"))))) }
    }
    "on qualified Type" - {
      "a.B" in { ImportSectionParser { "import a.B;" } should be(Right(Seq(Import(onePack("a", "B"))))) }
      "a.C" in { ImportSectionParser { "import a.C;" } should be(Right(Seq(Import(onePack("a", "C"))))) }
      "b.C" in { ImportSectionParser { "import b.C;" } should be(Right(Seq(Import(onePack("b", "C"))))) }
      "pack.Type" in { ImportSectionParser { "import pack.Type;" } should be(Right(Seq(Import(onePack("pack", "Type"))))) }

      "with multiple packages" - {
        "a.b.C" in { ImportSectionParser { "import a.b.C;" } should be(Right(Seq(Import(twoPack("a", "b", "C"))))) }
      }
    }
    "multiple imports" - {
      "A; B" in { ImportSectionParser { "import A;\nimport B;" } should be(Right(Seq(Import(qualifiedIdentifier("A")), Import(qualifiedIdentifier("B"))))) }
    }
  }
}
