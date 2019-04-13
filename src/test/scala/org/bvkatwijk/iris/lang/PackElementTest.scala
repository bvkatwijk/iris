package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.PackageElement
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class PackElementTest extends ParseTest {
  "PackElement" - {
    "accepts" - {
      "a" in packElement("a")
      "b" in packElement("b")
      "lowercase" in packElement("lowercase")
    }
    "denies" - {
      "A" in packElementCompileError("A")
      "mixedCase" in packElementCompileError("mixedCase")
      "numb3er" in packElementCompileError("numb3er")
      "$ymb*l$" in packElementCompileError("$ymb*l$")
      "contain space" in packElementCompileError("contain space")
    }
  }


  class LocalPackParser(val input: ParserInput) extends Parser with PackElementRule {
    def full = rule { packElement ~ EOI }
  }
  def packElement(value: String) = {
    run(value) should be(Right(PackageElement(value)))
  }
  def run(value: String) = new IsolatedParser()
    .parse(new LocalPackParser(value))(_.full)

  def packElementCompileError(str: String) = compileError(run(str))
}