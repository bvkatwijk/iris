package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.{Pack, PackageElement}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class PackRuleTest extends ParseTest {
  "PackRule" - {
    "accepts" - {
      "single" - {
        def single(value: String) = pack(value, Pack(Seq(PackageElement(value))))
        "a" in single("a")
        "b" in single("b")
        "name" in single("name")
      }
      "double" - {
        def double(value: String, first: String, second: String) = pack(value, Pack(Seq(PackageElement(first), PackageElement(second))))
        "a.b" in double("a.b", "a", "b")
        "a.c" in double("a.c", "a", "c")
        "b.c" in double("b.c", "b", "c")
        "first.second" in double("first.second", "first", "second")
      }
      "triple" - {
        def triple(value: String, first: String, second: String, third: String) = pack(value, Pack(Seq(PackageElement(first), PackageElement(second), PackageElement(third))))
        "a.b.c" in triple("a.b.c", "a", "b", "c")
        "first.second.third" in triple("first.second.third", "first", "second", "third")
      }
    }
    "denies" - {
      "A" in deny("A")
    }
  }

  class TestParser(val input: ParserInput) extends Parser with PackElementRule with PackRule {
    def full = rule { pack ~ EOI }
  }
  def pack(value: String, result: Pack) = {
    run(value) should be(Right(result))
  }
  def run(value: String) = new IsolatedParser()
    .parse(new TestParser(value))(_.full)

  def deny(str: String) = compileError(run(str))
}
