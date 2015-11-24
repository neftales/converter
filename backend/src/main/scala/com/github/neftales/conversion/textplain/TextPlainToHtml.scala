package com.github.neftales.conversion.textplain

import com.gilt.handlebars.scala.Handlebars
import com.gilt.handlebars.scala.binding.dynamic._
import com.typesafe.scalalogging.slf4j.LazyLogging

case object TextPlainToHtml extends Function[Seq[Byte], Seq[Byte]] with LazyLogging {

  val template =
    """<!DOCTYPE html>
    |<html lang="">
    | <head>
    |   <meta charset="UTF-8">
    |   <title></title>
    | </head>
    | <body>
    |   {{& body}}
    | </body>
    |</html>""".stripMargin

  def apply(file: Seq[Byte]): Seq[Byte] = {
      val t = Handlebars(template)
      val map = Map("body" -> readSeq(file).replace("\n", "<br>"))
      val textoSaida = t(map)

      logger.info(s" -> HTML")
      textoSaida.getBytes.toSeq
  }

}

