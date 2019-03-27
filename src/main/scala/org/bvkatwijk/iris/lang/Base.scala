package org.bvkatwijk.iris.lang

import org.parboiled2.{Parser, Rule0}

trait Base { self: Parser =>
  /**
    * Newlines
    * @return
    */
  def NL: Rule0 = rule { optional('\r') ~ '\n' }

  /**
    * Optional White Space
    * @return
    */
  def OWS: Rule0 = rule { zeroOrMore(' ') }
}
