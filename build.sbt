name := """convertAnyToOptionOfAny"""

version := "1.0"

scalaVersion := "2.11.7"

val finagleVersion = "6.29.0"

libraryDependencies ++= Seq(
  // Template
  "com.gilt" %% "handlebars-scala" % "2.0.1",

  // Image
  "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",

  // Finagle
  "com.twitter" %% "finagle-core" % finagleVersion,
  "com.twitter" %% "finagle-httpx" % finagleVersion,

  // Log
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1",
  "com.typesafe.scala-logging" % "scala-logging-slf4j_2.11" % "2.1.2",

  // Test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-simple")) }
