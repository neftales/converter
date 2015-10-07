name := """convertAnyToOptionOfAny"""

version := "1.0"

scalaVersion := "2.11.7"

val finagleVersion = "6.29.0"

libraryDependencies ++= Seq(
  //Template
  "com.gilt" %% "handlebars-scala" % "2.0.1",

  //Image
  "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",

  // Finagle
  "com.twitter" %% "finagle-core" % finagleVersion,
  "com.twitter" %% "finagle-httpx" % finagleVersion,

  // Test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


