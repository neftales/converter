
import com.typesafe.scalalogging.slf4j.LazyLogging
import conversion.image.Image
import conversion.textplain.TextPlainToHtml
import graph.ConvertGraph


object Applications extends App with LazyLogging {
  // 1. Construct graph
  val graph = new ConvertGraph
  // Starting graph with some nodes
  val nodes = List("TXT", "HTML", "PNG", "TIFF", "GIF", "BMP", "JPEG")
  graph(nodes)

  graph.connectWithWeightWithBehavior("TXT", List(("HTML", 1, TextPlainToHtml)))
  graph.connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  graph.connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  graph.connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  graph.connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG)))

  logger.info(s"graph nodes ${graph.nodes}")
  logger.info(s"graph edges ${graph.edges}")
}

