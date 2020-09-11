package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.{Identifier, Pack, QualifiedIdentifier}
import org.parboiled2.{Parser, Rule1}

trait QualifiedIdentifierRule {
  self: Parser with PackRule with IdentifierRule =>
  def qualifiedIdentifier: Rule1[QualifiedIdentifier] = rule {
    optionalPackage ~ identifier ~> ((a: Option[Pack],
                                      b: Identifier) =>
                                       QualifiedIdentifier(a, b))
  }

  def optionalPackage: Rule1[Option[Pack]] = rule {
    optional(packWithSeparator)
  }
}
