package org.bvkatwijk.iris.ast

case class Import(value: QualifiedIdentifier) {
  def asStatement: String = "import " + value.asFull + ";"
}
