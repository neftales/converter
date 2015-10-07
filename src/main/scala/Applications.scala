import java.net.InetSocketAddress

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.httpx.{Http, Request}
import graph.ConversionHandler

object Applications extends App {
  //setup service chain
  val serviceChain = new ConversionHandler

  //HTTP endpoint
  val socketAddress = new InetSocketAddress(8080)
  val server = ServerBuilder()
    .codec(Http())
    .bindTo(socketAddress)
    .name("HTTP endpoint")
    .build(serviceChain)

  println("microservice started")

}

