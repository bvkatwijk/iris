package org.bvkatwijk.iris.name

import org.bvkatwijk.iris.ParseTest

class NameParserTest extends ParseTest {
  "name" - {
    "a" in { NameParser { "a" } should be(Right("a")) }
    "can be \"name\"" in { NameParser { "name" } should be(Right("name")) }
    "can be \"mixedCase\"" in { NameParser { "mixedCase" } should be(Right("mixedCase")) }
  }
}
