package org.bvkatwijk.iris.ast

case class QualifiedIdentifier(pack: Option[Pack] = Option.empty, identifier: Identifier) {
  def javaClassName = identifier.name

  def asFull: String = Seq(identifier.name).mkString(pack.map(_.asQualification()).map(_ + ".").getOrElse(""), ".", "")
}

object QualifiedIdentifier {
  def unqualified(identifier: Identifier): QualifiedIdentifier = QualifiedIdentifier(Option.empty, identifier)
}
