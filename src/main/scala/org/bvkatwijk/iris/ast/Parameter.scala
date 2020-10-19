package org.bvkatwijk.iris.ast

case class Parameter(name: String, typeValue: QualifiedIdentifier) {
  def asJava: String = typeValue.asFull + " " + name
}

object Parameter {
  def unqualified(name: String, identifier: String): Parameter =
    Parameter(name, QualifiedIdentifier(None, Identifier(identifier)))
}
