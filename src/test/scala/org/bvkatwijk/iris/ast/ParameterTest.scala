package org.bvkatwijk.iris.ast

import org.scalatest.{FreeSpec, Matchers}

class ParameterTest extends FreeSpec with Matchers {
  "Parameter" - {
    "asJava" - {
      "a: B" in { Parameter.unqualified("a", "B").asJava should be("B a") }
      "a: C" in { Parameter.unqualified("a", "C").asJava should be("C a") }
      "b: C" in { Parameter.unqualified("b", "C").asJava should be("C b") }
    }

    "unqualified" - {
      "a: B" in { Parameter.unqualified("a", "B").name should be("a") }
    }
  }
}
