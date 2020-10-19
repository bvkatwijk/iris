package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.{Identifier, Parameter, QualifiedIdentifier}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class ParameterRuleTest extends ParseTest {
  "Parameter" - {
    "accepts" - {
      def accepts(value: String, result: Parameter) =
        run(value) should be(Right(result))

      "a: B" in accepts("a: B",
                        Parameter("a",
                                  QualifiedIdentifier(None, Identifier("B"))))
      "name: Type" in accepts(
        "name: Type",
        Parameter("name", QualifiedIdentifier(None, Identifier("Type")))
      )
      "no:Space" in accepts(
        "no:Space",
        Parameter("no", QualifiedIdentifier(None, Identifier("Space")))
      )
    }
    "denies" - {
      ": MissingName" in deny(": MissingName")
      "missing Colon" in deny("missing Colon")
      "missingType: " in deny("missingType: ")
    }
  }

  class TestParser(val input: ParserInput)
      extends Parser
      with Base
      with NameRule
      with PackElementRule
      with PackRule
      with IdentifierRule
      with QualifiedIdentifierRule
      with ParameterRule {
    def full = rule {
      parameter ~ EOI
    }
  }

  def run(value: String) =
    new IsolatedParser().parse(new TestParser(value))(_.full)

  def deny(str: String) = compileError(run(str))

}
