package org.bvkatwijk.iris.ast

import org.bvkatwijk.iris.BaseSpec

class PackElementTest extends BaseSpec {
  "PackageElement" - {
    "a" in { PackageElement("a").name should be("a") }
  }

}
