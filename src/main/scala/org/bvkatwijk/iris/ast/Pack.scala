package org.bvkatwijk.iris.ast

import scala.util.matching.Regex

case class Pack(elements: Seq[PackageElement]) {
  def asStatement(): String =
    elements.map(_.name).mkString("package ", ".", ";")

  def asQualification(): String = elements.map(_.name).mkString(".")
}

object Pack {
  def fromString(packageString: String): Pack =
    Pack(
      packageString
        .split("\\.")
        .map(PackageElement(_))
    )
}
