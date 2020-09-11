package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.Import
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class ImportParserTest extends ParseTest {
  "ImportParser" - {
    def actual(value: String) =
      new IsolatedParser().parse(new LocalImportParser(value))(_.full)

    def deny(value: String) = actual(value) should be('left)

    "on Type" - {
      "unqualified" - {
        def unqualified(value: String) =
          actual(s"import $value;") should be(
            Right(Import(qualifiedIdentifier(value))))

        "A" in unqualified("A")
        "B" in unqualified("B")
        "Type" in unqualified("Type")

        "denies" - {
          "a" in deny("import a;")
          "-" in deny("import -;")
        }
      }
      "qualified" - {
        "single" - {
          def single(pack: String, identifier: String) =
            actual("import " + pack + "." + identifier + ";") should be(
              Right(Import(onePack(pack, identifier))))

          "a.B" in single("a", "B")
          "a.C" in single("a", "C")
          "b.C" in single("b", "C")
          "pack.Type" in single("pack", "Type")
        }
        "multiple" - {
          "a.b.C" in {
            actual("import a.b.C;") should be(
              Right(Import(twoPack("a", "b", "C"))))
          }
        }
      }
    }
    "syntax" - {
      "needs a Type" in deny("import ;")
      "needs import keyword" in deny(" A;")
      "needs space after import" in deny("importA;")
    }
  }

  "importSection" - {
    def actual(value: String) =
      new IsolatedParser().parse(new LocalImportParser(value))(_.fullSection)

    def importSection(value: String, imports: Seq[Import]) =
      actual(value) should be(Right(imports))

    def deny(value: String) = actual(value) should be('left)

    "on Empty" - {
      "creates empty seq" in importSection("", Seq())
    }
    "on Type" - {
      "A" in importSection("import A;", Seq(Import(qualifiedIdentifier("A"))))
      "B" in importSection("import B;", Seq(Import(qualifiedIdentifier("B"))))
      "Type" in importSection("import Type;",
                              Seq(Import(qualifiedIdentifier("Type"))))
    }
    "on qualified Type" - {
      "a.B" in importSection("import a.B;", Seq(Import(onePack("a", "B"))))
      "a.C" in importSection("import a.C;", Seq(Import(onePack("a", "C"))))
      "b.C" in importSection("import b.C;", Seq(Import(onePack("b", "C"))))
      "pack.Type" in importSection("import pack.Type;",
                                   Seq(Import(onePack("pack", "Type"))))

      "with multiple packages" - {
        "a.b.C" in importSection("import a.b.C;",
                                 Seq(Import(twoPack("a", "b", "C"))))
      }
    }
    "multiple imports" - {
      "A; B" in importSection("import A;\nimport B;",
                              Seq(Import(qualifiedIdentifier("A")),
                                  Import(qualifiedIdentifier("B"))))
    }
  }

  class LocalImportParser(val input: ParserInput)
      extends Parser
      with Base
      with PackElementRule
      with PackRule
      with IdentifierRule
      with QualifiedIdentifierRule
      with ImportRule {
    def full = rule {
      importStatement ~ EOI
    }

    def fullSection = rule {
      importSection ~ EOI
    }
  }

}
