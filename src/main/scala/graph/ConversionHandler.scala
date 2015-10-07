package graph

import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{Request, Response}
import com.twitter.util.Future

class ConversionHandler extends Service[Request, Response] {

  override def apply(req: Request): Future[Response] = {
    val conversion = ConversionUtils.getConversion(req.getParam("from"), req.getParam("target"))

   val response = Response.decodeBytes(conversion(req.encodeBytes.toSeq).toArray)
   Future.value(response)
  }

}
