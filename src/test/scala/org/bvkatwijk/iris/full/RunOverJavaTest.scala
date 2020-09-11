package org.bvkatwijk.iris.full

import net.openhft.compiler.CompilerUtils
import org.bvkatwijk.iris.lang.ClassDefinitionParser
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class RunOverJavaTest extends AnyFreeSpec with Matchers {
  def compileAndRun(name: String, source: String): Invokable = {
    Invokable(
      CompilerUtils.CACHED_COMPILER
        .loadFromJava(name, irisToJava(source))
        .getDeclaredConstructor()
        .newInstance())
  }

  case class Invokable(sourceObject: Any) {
    def invoke(methodName: String): Object = {
      sourceObject.getClass
        .getDeclaredMethod(methodName)
        .invoke(sourceObject)
    }
  }

  def irisToJava(iris: String): String = {
    val result = ClassDefinitionParser(iris)
    if (result.isLeft) {
      throw new IllegalArgumentException(result.left.get.msg)
    } else {
      result.right.get.toJava
    }
  }
}
