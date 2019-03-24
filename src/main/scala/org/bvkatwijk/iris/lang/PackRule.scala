package org.bvkatwijk.iris.lang

import org.parboiled2.{CharPredicate, Parser, Rule0}

trait PackRule { self: Parser =>
  def pack: Rule0 = rule { oneOrMore(CharPredicate.LowerAlpha) }
}
