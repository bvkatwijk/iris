package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.Import
import org.parboiled2._

trait ImportRule {
  self: Parser
    with Base
    with PackElementRule
    with PackRule
    with IdentifierRule
    with QualifiedIdentifierRule =>

  def importSection: Rule1[Seq[Import]] = rule {
    zeroOrMore(importStatement).separatedBy(NL)
  }

  def importStatement: Rule1[Import] = rule {
    (atomic("import") ~ ' ' ~ OWS ~ qualifiedIdentifier ~ ';') ~> Import
  }
}
