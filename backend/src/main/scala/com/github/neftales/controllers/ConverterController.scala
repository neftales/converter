package com.github.neftales.controllers


import com.github.neftales.api.ConversionUtils
import org.scalatra.{NotFound, Ok}

import java.util.Base64

class ConverterController extends BaseController {

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
        logger.info(s"Conversão de ${data.start} para ${data.end} realizada com sucesso.")
        Ok(ResponseConversion(s"${data.end}", Base64.getEncoder.encodeToString(converted.toArray)))
      case None =>
        logger.info(s"Impossivel realizar a conversao de ${data.start} para ${data.end}")
        NotFound("")
    }

  }

}
