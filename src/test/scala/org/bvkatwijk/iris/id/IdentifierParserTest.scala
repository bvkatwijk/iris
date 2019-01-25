package org.bvkatwijk.iris

import org.bvkatwijk.iris.id.IdentifierParser
import org.bvkatwijk.iris.id.IdentifierParser.{Identifier, QualifiedIdentifier};

class IdentifierParserTest extends ParseTest {
  "qualifiedIdentifier" - {
    "on identifier" - {
      "A" in qualifiedIdentifier("A")
      "B" in qualifiedIdentifier("B")
      "Type" in qualifiedIdentifier("Type")
    }
    "on single packaged Type" - {
      "a.B" in qualifiedIdentifier("a.B")
      "a.C" in qualifiedIdentifier("a.C")
      "b.C" in qualifiedIdentifier("b.C")
      "some.Type" in qualifiedIdentifier("some.Type")
    }
    "on two packaged Type" - {
      "a.b.C" in qualifiedIdentifier("a.b.C")
      "some.packaged.Type" in qualifiedIdentifier("some.packaged.Type")
    }
  }

  "identifier" - {
    "A" in identifier("A")
    "B" in identifier("B")
    "can't be lowercase" in { IdentifierParser.identifier("a") should be('left) }
  }

  def qualifiedIdentifier(value: String) = IdentifierParser(value) should be(Right(QualifiedIdentifier(value)))
  def identifier(value: String) = IdentifierParser.identifier(value) should be(Right(Identifier(value)))
}
