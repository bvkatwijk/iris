package org.bvkatwijk.iris.name

import org.bvkatwijk.iris.ParseTest

class NameParserTest extends ParseTest {
  "name" - {
    "a" in name("a")
    "name" in name("name")
    "mixedCase" in name("mixedCase")

    "CompileError:" - {
      "Uppercase" in nameError("Uppercase")
    }
  }

  def name(value: String) = NameParser(value) should be(Right(value))
  def nameError(value: String) = compileError(NameParser(value))
}
