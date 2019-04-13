package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Identifier
import org.parboiled2.{CharPredicate, Parser, Rule1}

trait IdentifierRule { self: Parser =>
  def identifier: Rule1[Identifier] = rule {
    capture(CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha)) ~> Identifier
  }
}
