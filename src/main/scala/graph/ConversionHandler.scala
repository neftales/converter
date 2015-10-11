package graph

import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{Status, Request, Response}
import com.twitter.util.Future

class ConversionHandler extends Service[Request, Response] {

  override def apply(req: Request): Future[Response] = {
    ConversionUtils.getConversion(req.getParam("from"), req.getParam("target")) match {
      case Some(conversion) =>
        val response = Response.decodeBytes(conversion(req.encodeBytes.toSeq).toArray)
        Future.value(response)
      case _ => Future.value(Response.apply(Status.NotFound))
    }

  }

}
