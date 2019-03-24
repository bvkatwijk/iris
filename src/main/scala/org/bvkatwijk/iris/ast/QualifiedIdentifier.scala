package org.bvkatwijk.iris.ast

case class QualifiedIdentifier(pack: Option[Pack] = Option.empty, identifier: Identifier) {
  def javaClassName = identifier.name

}
object QualifiedIdentifier {
  def as_identifier(identifier: String): QualifiedIdentifier = QualifiedIdentifier(None, Identifier(identifier))
}

