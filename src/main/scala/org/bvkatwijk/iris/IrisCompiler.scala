package org.bvkatwijk.iris

object IrisCompiler {

  case class Flag(value: String)

  case class Setting(flag: Flag, value: String)

  def main(args: Array[String]): Unit = {
    val settings = processArguments(args)
  }

  def processArguments(args: Array[String]): Seq[Setting] =
    Seq()
}
