package com.github.neftales.conversion.textplain

import java.io.File

import com.gilt.handlebars.scala.Handlebars
import com.gilt.handlebars.scala.binding.dynamic._
import com.typesafe.scalalogging.slf4j.LazyLogging

case object TextPlainToHtml extends Function[Seq[Byte], Seq[Byte]] with LazyLogging {
  def apply(file: Seq[Byte]): Seq[Byte] = {

    val path = getClass.getClassLoader.getResource("templateHTML.html").getPath
    val template = new File(path)
    val t = Handlebars(template)
    val map = Map("body" -> readSeq(file))
    val textoSaida = t(map)

    logger.info(s" -> HTML")
    textoSaida.getBytes.toSeq
  }

}

