import graph.{ConvertGraph, Dijkstra}
import org.scalatest._

class DijkstraSpec extends FlatSpec with Matchers {

  val graph = new ConvertGraph
  // Starting graph with some nodes
  graph(List("TXT", "PDF", "HTML", "PNG", "FAX", "GIF"))

  graph.connectWith("TXT", List("PDF", "HTML", "PNG"))
  graph.connectWith("PDF", List("FAX", "PNG"))
  graph.connectWith("HTML", List("FAX"))
  graph.connectWith("PNG", List("FAX"))

  val txtToPDF = graph.getEdge("TXT", "PDF").setWeight(2)
  val txtToHTML = graph.getEdge("TXT", "HTML").setWeight(6)
  val txtToPNG = graph.getEdge("TXT", "PNG").setWeight(7)
  val pdfToFAX = graph.getEdge("PDF", "FAX").setWeight(6)
  val pdfToPNG = graph.getEdge("PDF", "PNG").setWeight(3)
  val htmlToFAX = graph.getEdge("HTML", "FAX").setWeight(1)
  val pngToFAX = graph.getEdge("PNG", "FAX").setWeight(5)

  val dijkstra = new Dijkstra[graph.type](graph)
  val (dis, path) = dijkstra.compute("TXT")

  "Dijkstra" should "find shortest path" in {
    dis("TXT") should be(0)
    dis("HTML") should be(6)
    dis("FAX") should be(7)
    dis("PDF") should be(2)
    dis("PNG") should be(5)
  }

  "Dijkstra" should "not find shortest path" in {
    dis("GIF") should be(Int.MaxValue)
  }

}
