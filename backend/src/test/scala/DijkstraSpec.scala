import com.github.neftales.conversion.image.Image
import com.github.neftales.graph.{ConvertGraph, Dijkstra}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class DijkstraSpec extends AnyFlatSpec with Matchers {

  val graph = new ConvertGraph

  graph(List("PNG", "TIFF", "GIF", "BMP", "JPEG", "SVG"))

  graph.connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  graph.connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  graph.connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  graph.connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG), ("JPEG", 3, Image.toJPEG)))


  val dijkstra = new Dijkstra[graph.type](graph)
  val (dis, path) = dijkstra.compute("GIF").get

  "Dijkstra" should "find shortest path" in {
    dis("PNG") shouldBe 1
    dis("TIFF") shouldBe 6
    dis("GIF") shouldBe 0
    dis("BMP") shouldBe 7
    dis("JPEG") shouldBe 2
  }

  "Dijkstra" should "not find shortest path" in {
    dis("SVG") shouldBe Int.MaxValue
  }

}
