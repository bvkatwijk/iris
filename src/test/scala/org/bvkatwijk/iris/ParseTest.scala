package org.bvkatwijk.iris

import org.bvkatwijk.iris.ast.{Identifier, Pack, PackageElement, QualifiedIdentifier}
import org.scalatest.{FreeSpec, Matchers}

class ParseTest extends FreeSpec with Matchers {
  def compileError(actual: Either[Any, Any]) = actual should be('left)
  def qualifiedIdentifier(name: String) = QualifiedIdentifier(None, Identifier(name))

  def twoPack(first: String, second: String, identifier: String) = {
    QualifiedIdentifier(Some(Pack(Seq(
      PackageElement(first),
      PackageElement(second)
    ))), Identifier(identifier))
  }
  def onePack(singlePack: String, identifier: String): QualifiedIdentifier = {
    QualifiedIdentifier(Some(Pack(Seq(PackageElement(singlePack)))), Identifier(identifier))
  }
}
