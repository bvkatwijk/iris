package org.bvkatwijk.iris.kw

import org.parboiled2._

object KeywordParser {

  object Keyword extends Enumeration {
    type Keyword = Value
    val IMPORT = Value
  }

}

class KeywordParser(val input: ParserInput) extends Parser {

  def importKeyword: Rule0 = rule { atomic("import") }

}
