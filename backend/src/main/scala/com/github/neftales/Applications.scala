package com.github.neftales

import com.typesafe.scalalogging.slf4j.LazyLogging
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

/**
 * @author neftales.antunes
 */
object Applications extends App with LazyLogging {

  val port = Option(System.getenv("PORT")) match {
    case Some(p) => p.toInt
    case None    => 8080
  }

  val server = new Server(port)
  val context = new WebAppContext()

  context setContextPath "/"
  context.setResourceBase("src/main/webapp")
  context.addEventListener(new ScalatraListener)
  context.addServlet(classOf[DefaultServlet], "/")

  server.setHandler(context)

  server.start()
  server.join()
}
