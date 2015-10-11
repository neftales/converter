import java.net.InetSocketAddress

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.httpx.Http
import com.typesafe.scalalogging.slf4j.LazyLogging
import graph.ConversionHandler

object Applications extends App with LazyLogging {
  //setup service chain
  val service = new ConversionHandler

  //HTTP endpoint
  val socketAddress = new InetSocketAddress(8080)
  val server = ServerBuilder()
    .codec(Http())
    .bindTo(socketAddress)
    .name("HTTP endpoint")
    .build(service)

  logger.info("Serviço iniciado.")

}

