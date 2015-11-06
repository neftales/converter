import org.scalatra.sbt._

lazy val commonSettings = Seq(
  organization := "br.com.neftales",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

val JettyVersion = "9.3.5.v20151012"
val Json4sVersion = "3.2.11"
val ScalatraVersion = "2.4.0.RC1"

lazy val converter = (project in file("."))
  .settings(ScalatraPlugin.scalatraWithJRebel: _*)
  .settings(commonSettings: _*)
  .settings(name := "convertAnyToOptionOfAny",
    resolvers ++= Seq(
      "tpolecat" at "http://dl.bintray.com/tpolecat/maven",
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
    ),
    libraryDependencies ++= Seq(
      // Jetty
      "org.eclipse.jetty" % "jetty-server" % JettyVersion % "container",
      "org.eclipse.jetty" % "jetty-servlet" % JettyVersion % "container",
      "org.eclipse.jetty" % "jetty-plus" % JettyVersion % "container",
      "org.eclipse.jetty" % "jetty-webapp" % JettyVersion % "container;compile",

      // Template
      "com.gilt" %% "handlebars-scala" % "2.0.1",

      // Image
      "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",

      // Log
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1",
      "com.typesafe.scala-logging" % "scala-logging-slf4j_2.11" % "2.1.2",

      // Scalatra
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-auth" % ScalatraVersion,

      // Json
      "org.scalatra" %% "scalatra-json" % ScalatraVersion,
      "org.json4s" %% "json4s-jackson" % Json4sVersion,
      "org.json4s" %% "json4s-ext" % Json4sVersion,

      // Test
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test"
    ),
    libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-simple")) }
  )
