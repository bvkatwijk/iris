package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.PackageElement
import org.parboiled2.{CharPredicate, Parser, Rule1}

trait PackElementRule {
  self: Parser =>
  def packElement: Rule1[PackageElement] = rule {
    capture(oneOrMore(CharPredicate.LowerAlpha)) ~> (PackageElement)
  }

  def packageElementSeparator = rule {
    '.'
  }
}
