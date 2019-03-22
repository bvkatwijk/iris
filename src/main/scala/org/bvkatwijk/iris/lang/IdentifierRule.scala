package org.bvkatwijk.iris.lang

import org.parboiled2.{CharPredicate, Parser, Rule0}

trait IdentifierRule { self: Parser =>
  def identifier: Rule0 = rule { CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha) }
}
