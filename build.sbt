scalaVersion := "2.13.1"

name := "iris"
organization := "org.bvkatwijk.iris"
version := "0.1.0"

libraryDependencies ++= Seq(
  // Parsing
  "org.parboiled" %% "parboiled" % "2.2.0",

  // Java Compilation and Exectution
  "net.openhft" % "compiler" % "2.3.4",

  "org.scalatest" %% "scalatest" % "3.2.2" % Test,
)
