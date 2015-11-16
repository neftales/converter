package com.github.neftales.api

import com.github.neftales.graph.{ConvertGraph, Dijkstra}

object ConversionUtils {

  def getConversion(start: String, end: String)(implicit graph: ConvertGraph): Option[Seq[Byte] => Seq[Byte]] = {
    val dijkstra = new Dijkstra[graph.type](graph)
    val nodeA = graph.getNode(start)
    val nodeB = graph.getNode(end)

    (nodeA, nodeB) match {
      case (Some(a), Some(b)) =>
        dijkstra.mountListOfEdge(a, b) match {
          case Some(list) =>
            val listBehavior = list map { edge =>
              edge.getBehavior
            }

            val compose = listBehavior.reduceLeft { (f, g) =>
              f andThen g
            }

            Some(compose)

          case _ => None
        }
      case _ => None
    }

  }

  def getNodes()(implicit graph: ConvertGraph) = {
    graph.nodes map {
      node => node.toString
    }
  }

  def getNodes(node: String)(implicit graph: ConvertGraph): Option[List[(graph.Node, Int)]] = {
    val dijkstra = new Dijkstra[graph.type](graph)

    val list: Option[List[(dijkstra.Node, Int)]] = graph.getNode(node) match {
      case Some(node) =>
        val (dist, path) = dijkstra.compute(node)
        Some(dist.toList.filter(p => p._2 != Int.MaxValue && p._2 > 0))
      case _ => None
    }
    list
  }

}