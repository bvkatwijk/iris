package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Pack
import org.parboiled2.{Parser, Rule1}

trait PackRule { self: Parser with PackElementRule =>
  def pack: Rule1[Pack] = rule {
    oneOrMore(packElement).separatedBy(packageElementSeparator) ~> (Pack(_))
  }

  def packWithSeparator: Rule1[Pack] = rule {
    pack ~ packageElementSeparator
  }
}
