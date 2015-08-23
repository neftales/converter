import graph.ConvertGraph

object Applications extends App {
  // 1. Construct graph
  val g = new ConvertGraph
  val textPlain = g.addNode("TXT")
  val html = g.addNode("HTML")
  val pdf = g.addNode("PDF")

  println(g.nodes)
}

