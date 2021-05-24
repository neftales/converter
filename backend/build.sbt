import org.scalatra.sbt._

lazy val commonSettings = Seq(
  organization := "br.com.neftales",
  version := "0.1.0",
  scalaVersion := "2.13.6"
)

val JettyVersion = "9.4.41.v20210516"
val Json4sVersion = "3.6.6"
val ScalatraVersion = "2.7.+"

lazy val converter = (project in file("."))
  .settings(ScalatraPlugin.scalatraWithJRebel: _*)
  .settings(commonSettings: _*)
  .settings(name := "converter-api",
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

      // Image
      "com.github.jai-imageio" % "jai-imageio-core" % "1.3.0",

      // Log
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3",

      // Scalatra
      "org.scalatra" %% "scalatra" % ScalatraVersion,

      // Json
      "org.scalatra" %% "scalatra-json" % ScalatraVersion,
      "org.json4s" %% "json4s-jackson" % Json4sVersion,
      "org.json4s" %% "json4s-ext" % Json4sVersion,

        // Test
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "org.scalatest" %% "scalatest" % "3.2.9" % "test"
    )
  )
