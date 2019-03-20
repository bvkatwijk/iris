package org.bvkatwijk.iris.full

import net.openhft.compiler.CompilerUtils
import org.bvkatwijk.iris.lang.ClassDefinitionParser
import org.scalatest.{FreeSpec, Matchers}

class HelloTest extends FreeSpec with Matchers {
  def classOutputFolder = "./"

  "call toString on value class" - {
    "compiles and returns '[Hello]" in { compileAndRun("Hello", "class Hello() {}").invoke("toString") should be("[Hello]") }
    "compiles and returns '[Other]" in { compileAndRun("Other", "class Other() {}").invoke("toString") should be("[Other]") }
  }

  def compileAndRun(name: String, source: String): Invokable = {
    Invokable(CompilerUtils.CACHED_COMPILER
      .loadFromJava(name, irisToJava(source))
      .newInstance())
  }

  case class Invokable(sourceObject: Any) {
    def invoke(methodName: String): Object = {
      sourceObject
          .getClass
          .getDeclaredMethod(methodName)
          .invoke(sourceObject)
    }
  }

  def irisToJava(iris: String): String = {
    val result = ClassDefinitionParser(iris)
    if(result.isLeft) {
      throw new IllegalArgumentException(result.left.get.msg)
    } else {
      result.right.get.toJava
    }
  }
}
