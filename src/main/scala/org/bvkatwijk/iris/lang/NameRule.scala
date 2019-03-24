package org.bvkatwijk.iris.lang

import org.parboiled2.{CharPredicate, Parser, Rule1}

trait NameRule { self: Parser =>
  def name: Rule1[String] = rule {
    capture(oneOrMore(CharPredicate.LowerAlpha) ~ zeroOrMore(CharPredicate.Alpha))
  }

  def methodName: Rule1[String] = rule { capture(CharPredicate.LowerAlpha ~ zeroOrMore(methodNameCharacter)) }

  def methodNameCharacter: CharPredicate = CharPredicate.AlphaNum ++ '_'
}
