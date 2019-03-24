package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Identifier
import org.parboiled2.{CharPredicate, Parser, Rule0, Rule1}

trait IdentifierRule { self: Parser =>
  def identifier: Rule0 = rule { CharPredicate.UpperAlpha ~ zeroOrMore(CharPredicate.Alpha) }
  def captureIdentifier: Rule1[Identifier] = rule { capture(identifier) ~> Identifier }
}
