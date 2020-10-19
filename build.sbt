scalaVersion := "2.13.1"

name := "iris"
organization := "org.bvkatwijk.iris"
version := "0.1.0"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.parboiled" %% "parboiled" % "2.2.0",
      "net.openhft" % "compiler" % "2.3.4",
      "org.scalatest" %% "scalatest" % "3.2.2" % "it,test"
    )
  )
