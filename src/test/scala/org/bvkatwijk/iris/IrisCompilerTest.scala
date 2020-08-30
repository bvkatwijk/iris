package org.bvkatwijk.iris

class IrisCompilerTest extends BaseSpec {

  ".processArguments" - {
    "with empty args returns empty set" in {
      IrisCompiler.processArguments(Array("")) should be(Seq())
    }
  }

}
