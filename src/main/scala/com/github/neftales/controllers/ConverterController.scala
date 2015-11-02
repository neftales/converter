package com.github.neftales.controllers


import java.net.InetAddress

import com.github.neftales.api.ConversionUtils
import com.typesafe.scalalogging.slf4j.LazyLogging

/**
 * Created by Neftales on 01/11/2015.
 */
class ConverterController extends BaseController with LazyLogging {

  get("/") {
    val host = InetAddress.getLocalHost.getHostName
    logger.info(host)
    ConversionUtils.getNodes()
  }

}
