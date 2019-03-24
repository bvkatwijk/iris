package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.{Identifier, QualifiedIdentifier};

class IdentifierParserTest extends ParseTest {
  "qualifiedIdentifier" - {
    "on identifier" - {
      "A" in testQualifiedIdentifier("A")
      "B" in testQualifiedIdentifier("B")
      "Type" in testQualifiedIdentifier("Type")
    }
    "on single packaged Type" - {
      "a.B" in qi("a.B", onePack("a", "B"))
      "a.C" in qi("a.C", onePack("a", "C"))
      "b.C" in qi("b.C", onePack("b", "C"))
      "some.Type" in qi("some.Type", onePack("some", "Type"))
    }
    "on two packaged Type" - {
      "a.b.C" in qi("a.b.C", twoPack("a", "b", "C"))
      "some.packaged.Type" in qi("some.packaged.Type", twoPack("some", "packaged", "Type"))
    }
  }
  "identifier" - {
    "A" in identifier("A")
    "B" in identifier("B")
    "can't be lowercase" in identifierError("a")
    "can't be a number" in identifierError("1")
    "can't start with a number" in identifierError("1name")
  }
  def qi(value: String, expected: QualifiedIdentifier) = {
    IdentifierParser(value) should be(Right(expected))
  }
  def testQualifiedIdentifier(value: String) = qi(value, qualifiedIdentifier(value))
  def identifier(value: String) = IdentifierParser.identifier(value) should be(Right(Identifier(value)))
  def identifierError(value: String) = compileError(IdentifierParser.identifier(value))
}
