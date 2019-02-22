package org.bvkatwijk.iris.full

import java.io.File
import java.net.{URL, URLClassLoader}
import javax.tools.DiagnosticListener

import org.bvkatwijk.iris.cd.ClassDefinitionParser
import org.scalatest.{FreeSpec, Matchers}

import scala.io.Source
import net.openhft.compiler.CompilerUtils

class HelloTest extends FreeSpec with Matchers {
  def classOutputFolder = "./"

  "hello.iris" - {
    "compiles and returns 'Hello World!" in { compileAndRun("class Hello() {}") should be("Hello World!") }
  }

  def compileAndRun(source: String): String = {
    CompilerUtils.CACHED_COMPILER
      .loadFromJava("Hello", irisToJava(source))
      .newInstance()
      .toString()
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
