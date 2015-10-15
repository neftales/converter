import conversion.image.Image
import conversion.textplain.TextPlainToHtml

package object graph {

  // 1. Construct graph
  implicit val converteGraph = new ConvertGraph
  // Starting graph with some nodes
  converteGraph(List("TXT", "HTML", "PNG", "TIFF", "GIF", "BMP", "JPEG"))

  converteGraph.connectWithWeightWithBehavior("TXT", List(("HTML", 1, TextPlainToHtml)))
  converteGraph.connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  converteGraph.connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  converteGraph.connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  converteGraph.connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG)))
}
