package org.bvkatwijk.iris

import org.scalatest.{FreeSpec, Matchers}

class ParseTest extends FreeSpec with Matchers {
  def compileError(actual: Either[Any, Any]) = actual should be('left)
}
