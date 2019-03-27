package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.ast.{MethodDeclaration, Parameter}
import org.bvkatwijk.iris.parser.IsolatedParser
import org.parboiled2.{Parser, ParserInput}

class MethodDeclarationParserTest extends ParseTest {
  "method" - {
    "def a(b: C): D = {}" in {
      run("def a(b: C): D = {}") should be(Right(
        MethodDeclaration(
          "a",
          Seq(Parameter("b", qualifiedIdentifier("C"))),
          qualifiedIdentifier("D"))))
    }
    "def name(value: Type): ReturnType = {}" in {
      run("def name(value: Type): ReturnType = {}") should be(Right(
        MethodDeclaration(
          "name",
          Seq(Parameter("value", qualifiedIdentifier("Type"))),
          qualifiedIdentifier("ReturnType"))))
    }
    "keyword" - {
      def methodKeyword(key: String)  = methodDeclaration(s"$key name(): B = {}").right.get.name should be("name")

      "is def" in methodKeyword("def")
    }
    "name" - {
      def name(name: String) = methodDeclaration(s"def $name(): B = {}").right.get.name should be(name)
      def nameError(name: String) = compileError(methodDeclaration(s"def $name(): B = {}"))

      "can be lowercase" in name("a")
      "can be long" in name("veryveryveryveryveryvery")
      "can be mixedCase" in name("mixedCase")
      "can be snake_case" in name("snake_case")
      "can contain numbers" in name("contains1Number")
      "can end with numbers" in name("endsWithNumbersLike123")
      "can't start with uppercase letter" in nameError("Startwithuppercase")
      "can't start with number" in nameError("1StartwithNumber")
      "can't start with underscore" in nameError("_start_with_underscore")
      "can't contain spaces" in nameError("contains spaces")
      "can't contain hyphens" in nameError("contains-hypens")
      "can't end with space" in nameError("spaceAfterName ")
    }
    "parameter" - {
      def paramCount(params: String, expectedCount: Int) = methodDeclaration(s"def a($params): D = {}").right.get.parameters.length should be(expectedCount)

      "amount" - {
        "can be zero" in paramCount("", 0)
        "can be one" in paramCount("one: Type", 1)
        "can be two" in paramCount("one: Type, two: Type", 2)
        "can be three" in paramCount("one: Type, two: Type, three: Type", 3)
      }
    }
    def methodDeclaration(value: String) = run(value)
  }

  class TestParser(val input: ParserInput) extends Parser
    with Base
    with NameRule
    with PackElementRule
    with PackRule
    with IdentifierRule
    with QualifiedIdentifierRule
    with ParameterRule
    with MethodRule
  {
    def full = rule { methodDeclaration ~ EOI }
  }

  def run(value: String) = new IsolatedParser().parse(new TestParser(value))(_.full)

}
