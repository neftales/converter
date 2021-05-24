package com.github.neftales.controllers

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.scalatra.{CorsSupport, FutureSupport, ScalatraServlet}

import scala.concurrent.ExecutionContext
import com.github.neftales.api.ConversionFormats
import com.typesafe.scalalogging.LazyLogging

import java.util.concurrent.Executors

trait BaseController extends ScalatraServlet
with JacksonJsonSupport
with FutureSupport
with LazyLogging
with CorsSupport {

  protected implicit lazy val jsonFormats: Formats = ConversionFormats
  protected implicit val executor: ExecutionContext = ExecutionContext.fromExecutorService(
    Executors.newFixedThreadPool(6)
  )

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
