package org.bvkatwijk.iris.full

class HelloTest extends RunOverJavaTest {
  "call toString on value class" - {
    "compiles and returns '[Hello]'" in {
      compileAndRun("Hello", "class Hello() {}").invoke("toString") should be(
        "[Hello]"
      )
    }
    "compiles and returns '[Other]'" in {
      compileAndRun("Other", "class Other() {}").invoke("toString") should be(
        "[Other]"
      )
    }
  }
}
