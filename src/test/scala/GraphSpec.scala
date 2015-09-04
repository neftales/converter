import graph.{Dijkstra, ConvertGraph}
import org.scalatest._

class GraphSpec extends FlatSpec with Matchers {

  val graph = new ConvertGraph
  val txt = graph.addNode("TXT")
  val pdf = graph.addNode("PDF")
  val html = graph.addNode("HTML")
  val png = graph.addNode("PNG")
  val fax = graph.addNode("FAX")
  val gif = graph.addNode("GIF")

  txt.connectWith(pdf).setWeight(2)
  txt.connectWith(html).setWeight(6)
  txt.connectWith(png).setWeight(7)
  pdf.connectWith(fax).setWeight(6)
  pdf.connectWith(png).setWeight(3)
  html.connectWith(fax).setWeight(1)
  png.connectWith(fax).setWeight(5)

  val dijkstra = new Dijkstra[graph.type](graph)
  val (dis, path) = dijkstra.compute(txt)

  "Dijkstra" should "find shortest path" in {
    dis(txt) should be(0)
    dis(html) should be(6)
    dis(fax) should be(7)
    dis(pdf) should be(2)
    dis(png) should be(5)
  }

  "Dijkstra" should "not find shortest path" in {
    dis(gif) should be(Int.MaxValue)
  }

}
