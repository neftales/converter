name := """convertAnyToOptionOfAny"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  //Template
  "com.gilt" %% "handlebars-scala" % "2.0.1",

  //Image
  "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",

  // Test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


