package org.bvkatwijk.iris

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class IrisCompilerTest extends FreeSpec with Matchers {

  ".processArguments" - {
    "with empty args returns empty set" in {
      IrisCompiler.processArguments(Array("")) should be(Seq())
    }
  }

}
