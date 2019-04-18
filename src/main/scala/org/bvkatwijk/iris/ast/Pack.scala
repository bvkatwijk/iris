package org.bvkatwijk.iris.ast

case class Pack(elements: Seq[PackageElement]) {
  def asStatement(): String = elements.map(_.name).mkString("package ", ".", ";")

  def asQualification(): String = elements.map(_.name).mkString(".")
}
