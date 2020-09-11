package org.bvkatwijk.iris.lang

import org.bvkatwijk.iris.ast.FunctionReference
import org.parboiled2.Parser

trait FunctionReferenceRule {
  this: Parser with IdentifierRule with NameRule =>
  def functionReference = rule {
    identifier ~ atomic("::") ~ methodName ~> FunctionReference
  }
}
