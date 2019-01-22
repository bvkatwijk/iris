package org.bvkatwijk.iris

import org.scalatest.FreeSpec
import org.bvkatwijk.iris.id.IdentifierParser;
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier;
import org.scalatest.Matchers

class IdentifierParserTest extends FreeSpec with Matchers {
  "on Type" - {
    "A" in { IdentifierParser { "A" } should be(Right(new QualifiedIdentifier("A"))) }
    "B" in { IdentifierParser { "B" } should be(Right(new QualifiedIdentifier("B"))) }
    "Type" in { IdentifierParser { "Type" } should be(Right(new QualifiedIdentifier("Type"))) }
  }
  "on single packaged Type" - {
    "a.B" in { IdentifierParser { "a.B" } should be(Right(new QualifiedIdentifier("a.B"))) }
    "a.C" in { IdentifierParser { "a.C" } should be(Right(new QualifiedIdentifier("a.C"))) }
    "b.C" in { IdentifierParser { "b.C" } should be(Right(new QualifiedIdentifier("b.C"))) }
    "some.Type" in { IdentifierParser { "some.Type" } should be(Right(new QualifiedIdentifier("some.Type"))) }
  }
  "on two packaged Type" - {
    "a.b.C" in { IdentifierParser { "a.b.C" } should be(Right(new QualifiedIdentifier("a.b.C"))) }
    "some.packaged.Type" in { IdentifierParser { "some.packaged.Type" } should be(Right(new QualifiedIdentifier("some.packaged.Type"))) }
  }
}
