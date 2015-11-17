package com.github.neftales.controllers


import com.github.neftales.api.ConversionUtils
import com.typesafe.scalalogging.slf4j.LazyLogging
import net.iharder.Base64
import org.scalatra.{NotFound, Ok}

class ConverterController extends BaseController with LazyLogging {

  get("/nodes") {
    logger.info(s"Consultando lista de formatos disponiveis")
    ConversionUtils.getNodes()
  }

  get("/from/:node") {
    val node = params("node").toUpperCase
    logger.info(s"Consultando conversões possíveis a partir de $node")

    ConversionUtils.getNodes(node) match {
      case Some(x) => x map { tuple =>
        NodeResponse(tuple._1.toString, tuple._2)
      }

      case _ => NotFound("")
    }
  }

  post("/?") {
    val data: RequestConversion = parsedBody.extract[RequestConversion]

    logger.info(s"Realizando conversao de ${data.start} para ${data.end}")
    ConversionUtils.getConversion(data.start, data.end) match {
      case Some(function) =>
        logger.info(s"${data.start}")
        val converted = function(data.content.toSeq)
        Ok(ResponseConversion(s"${data.end}", Base64.encodeBytes(converted.toArray)))
      case None =>
        logger.info(s"Impossivel realizar a conversao de ${data.start} para ${data.end}")
        NotFound("")
    }

  }

}
