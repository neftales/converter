package com.github.neftales.api

import com.github.neftales.controllers.serializers.RequestConversionSerializer
import org.json4s.{DefaultFormats, Serializer}

object ConversionFormats extends DefaultFormats {
  override val customSerializers: List[Serializer[_]] = List(RequestConversionSerializer)
}
