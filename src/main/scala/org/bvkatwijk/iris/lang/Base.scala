package org.bvkatwijk.iris.lang

import org.parboiled2.{Parser, Rule0}

trait Base { self: Parser =>
  def NL: Rule0 = rule { optional('\r') ~ '\n' }
  def OWS: Rule0 = rule { zeroOrMore(' ') }
}
