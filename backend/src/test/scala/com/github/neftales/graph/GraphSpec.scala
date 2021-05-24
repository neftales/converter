package com.github.neftales.graph

import org.scalatest.{Matchers, FlatSpec}
import com.github.neftales.graph.converteGraph

class GraphSpec extends FlatSpec with Matchers {

  "Graph" should "return a Some(GIF)" in {
    converteGraph.getNode("GIF") match {
      case Some(x) => x.toString shouldEqual "GIF"
      case None => fail("Impossível recuperar o node GIF")
    }

  }

  "Graph" should "return a None for node PDF" in {
    converteGraph.getNode("PDF") match {
      case Some(x) => fail(s"$x != de None")
      case x => x === None
    }

  }


  "Graph" should "return a Egde " in {
    converteGraph.getEdge("PNG", "JPEG") match {
      case Some(x) => x.toString shouldEqual "PNG -> JPEG"
      case None => fail("Impossível recuperar a aresta PNG -> JPEG")
    }

  }

  "Graph" should "return a None for a Edge(EXE, PDF)" in {
    converteGraph.getEdge("EXE", "PDF") match {
      case Some(x) => fail(s"$x != de None")
      case x => x === None
    }

  }

}
