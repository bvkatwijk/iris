scalaVersion := "2.12.3"

name := "iris"
organization := "org.bvkatwijk.iris"
version := "0.1.0"

libraryDependencies ++= Seq(
  // Parsing
  "org.parboiled" %% "parboiled" % "2.1.4",

  // Java Compilation and Exectution
  "net.openhft" % "compiler" % "2.3.4",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
)
