package com.github.neftales.controllers


import com.github.neftales.api.ConversionUtils
import com.typesafe.scalalogging.slf4j.LazyLogging

class ConverterController extends BaseController with LazyLogging {

  get("/nodes/") {
    ConversionUtils.getNodes()
  }

}
