package com.github.neftales.controllers.serializers

import com.github.neftales.controllers.RequestConversion
import net.iharder.Base64
import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JField, JObject, JString}

object RequestConversionSerializer extends CustomSerializer[RequestConversion](format => (
  {
    case JObject(
    JField("start", JString(start)) :: JField("end", JString(end)) ::
      JField("content", JString(content)) :: _) => RequestConversion(start, end, Base64.decode(content))
  },
  {
    case requestConversion: RequestConversion =>
      JObject(
        JField("start", JString(requestConversion.start)) ::
        JField("end", JString(requestConversion.end)) ::
        JField("content", JString(Base64.encodeBytes(requestConversion.content))) :: Nil
      )
  })

)
