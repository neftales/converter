import conversion.textplain.{TextPlainToHtml, TextPlainToPdf}
import graph.{ConvertGraph, Dijkstra}
import org.scalatest._

class DijkstraSpec extends FlatSpec with Matchers {

  val graph = new ConvertGraph
  // Starting graph with some nodes
  graph(List("TXT", "PDF", "HTML", "PNG", "FAX", "GIF"))

  graph.connectWithWeightWithBehavior("TXT", List(("PDF", 2, TextPlainToPdf), ("HTML", 6, TextPlainToHtml), ("PNG", 7, TextPlainToPdf)))
  graph.connectWithWeight ("PDF", List(("FAX", 6), ("PNG", 3)))
  graph.connectWithWeight("HTML", List(("FAX", 1)))
  graph.connectWithWeight("PNG", List(("FAX", 5)))

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
