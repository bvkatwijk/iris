package org.bvkatwijk.iris.ast

case class Pack(elements: Seq[PackageElement]) {
  def asStatement(): String = "package " + elements.map(_.name).mkString(".") + ";"
}
