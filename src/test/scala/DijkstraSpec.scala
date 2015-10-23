import com.github.neftales.conversion.textplain.{TextPlainToHtml, TextPlainToPdf}
import com.github.neftales.graph.{ConvertGraph, Dijkstra}
import org.scalatest._

class DijkstraSpec extends FlatSpec with Matchers {

  val graph = new ConvertGraph
  // Starting com.github.neftales.graph with some nodes
  graph(List("TXT", "PDF", "HTML", "PNG", "FAX", "GIF"))

  graph.connectWithWeightWithBehavior("TXT", List(("PDF", 2, TextPlainToPdf), ("HTML", 6, TextPlainToHtml), ("PNG", 7, TextPlainToPdf)))
  graph.connectWithWeight("PDF", List(("FAX", 6), ("PNG", 3)))
  graph.connectWithWeight("HTML", List(("FAX", 1)))
  graph.connectWithWeight("PNG", List(("FAX", 5)))

  val dijkstra = new Dijkstra[graph.type](graph)
  val (dis, path) = dijkstra.compute("TXT").get

  "Dijkstra" should "find shortest path" in {
    dis("TXT") shouldBe 0
    dis("HTML") shouldBe 6
    dis("FAX") shouldBe 7
    dis("PDF") shouldBe 2
    dis("PNG") shouldBe 5
  }

  "Dijkstra" should "not find shortest path" in {
    dis("GIF") shouldBe Int.MaxValue
  }

}
