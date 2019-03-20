package org.bvkatwijk.iris.lang

import org.parboiled2.{Parser, Rule0}

trait Base { self: Parser =>
  def OWS: Rule0 = rule { zeroOrMore(' ') }
}
