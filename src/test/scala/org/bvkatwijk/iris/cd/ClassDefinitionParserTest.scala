package org.bvkatwijk.iris.cd

import org.bvkatwijk.iris.ParseTest
import org.bvkatwijk.iris.cd.ClassDefinitionParser.ClassDefinition
import org.bvkatwijk.iris.id.IdentifierParser.QualifiedIdentifier

class ClassDefinitionParserTest extends ParseTest {
  "classDefinition" - {
    def classDefinition(value: String) = ClassDefinitionParser(value)

    def classOf(value: String) = Right(ClassDefinition(QualifiedIdentifier(value)))

    "type" - {
      def classType(str: String) = classDefinition(s"class $str {}") should be(classOf(str))

      "A" in classType("A")
      "B" in classType("B")
      "Type" in classType("Type")
    }

    "with constructor" - {
      def classConstructorType(str: String) = classDefinition(s"class $str {}")

      "empty" - {
        "A()" in { classConstructorType("A()") should be(classOf("A")) }
        "B()" in { classConstructorType("B()") should be(classOf("B")) }
      }
    }
  }
}
