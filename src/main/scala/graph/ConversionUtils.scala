package graph

class ConversionUtils {

  def getConversion(start: String, end: String, graph: ConvertGraph): Seq[Byte] => Seq[Byte] = {
    val dijkstra = new Dijkstra[graph.type](graph)
    val list = dijkstra.mountListOfEdge(graph.getNode(start), graph.getNode(end))

    val listBehavior = list map { edge =>
      edge.getBehavior
    }

    listBehavior.reduceLeft { (f, g) =>
      f andThen g
    }
  }

}
