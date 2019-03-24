package org.bvkatwijk.iris.ast

case class QualifiedIdentifier(value: String) {
  def javaClassName = value
}
