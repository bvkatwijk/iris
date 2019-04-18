package org.bvkatwijk.iris.ast

import org.scalatest.{FreeSpec, Matchers}

class PackElementTest extends FreeSpec with Matchers {
  "PackageElement" - {
    "a" in { PackageElement("a").name should be("a") }
  }

}
