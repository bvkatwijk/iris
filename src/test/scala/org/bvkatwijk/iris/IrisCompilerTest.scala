package org.bvkatwijk.iris

import org.scalatest.{FreeSpec, Matchers}

class IrisCompilerTest extends FreeSpec with Matchers {

  ".processArguments" - {
    "with empty args returns empty set" in {
      IrisCompiler.processArguments(Array("")) should be(Seq())
    }
  }

}
