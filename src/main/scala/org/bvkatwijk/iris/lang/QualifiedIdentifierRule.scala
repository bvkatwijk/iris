package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.QualifiedIdentifier
import org.parboiled2.{Parser, Rule1}

trait QualifiedIdentifierRule { self: Parser with PackRule with IdentifierRule=>
  def qualifiedIdentifier: Rule1[QualifiedIdentifier] = rule {
    capture(zeroOrMore(pack ~ '.') ~ identifier) ~> QualifiedIdentifier
  }
}
