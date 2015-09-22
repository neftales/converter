
import conversion.image.Image
import conversion.textplain.TextPlainToHtml
import graph.{ConvertGraph, Dijkstra}


object Applications extends App {
  // 1. Construct graph
  val graph = new ConvertGraph
  // Starting graph with some nodes
  graph(List("TXT", "HTML", "PNG", "TIFF", "GIF", "BMP", "JPEG"))

  graph connectWithWeightWithBehavior("TXT", List(("HTML", 1, TextPlainToHtml)))
  graph connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  graph connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  graph connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  graph connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG)))

  val dijkstra = new Dijkstra[graph.type](graph)
  val list = dijkstra.mountListOfEdge(graph.getNode("PNG"), graph.getNode("GIF"))

  list foreach (x => println(x))
}

