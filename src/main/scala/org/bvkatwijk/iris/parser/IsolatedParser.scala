package org.bvkatwijk.iris.parser

import org.bvkatwijk.iris.lang.CompileError
import org.parboiled2.{Parser, Rule1}

class IsolatedParser {
  def parse[P <: Parser, A](p: P)(f: P => Rule1[A]): Either[CompileError, A] = {
    import Parser.DeliveryScheme.Either
    p.__run(f(p)).left.map(error => CompileError(p.formatError(error)))
  }
}
