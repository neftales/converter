package com.github.neftales.controllers

import com.github.neftales.applicationExecutor
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{FutureSupport, ScalatraServlet}
import scala.concurrent.ExecutionContext
import com.github.neftales.api.ConversionFormats

trait BaseController extends ScalatraServlet
with JacksonJsonSupport
with FutureSupport
with LazyLogging {

  protected implicit lazy val jsonFormats: Formats = ConversionFormats
  protected implicit val executor: ExecutionContext = applicationExecutor

  options("/*") {
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

  before() {
    contentType = formats("json")
  }

  notFound {
    halt(404)
  }

}
