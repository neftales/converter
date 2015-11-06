package com.github.neftales

import com.github.neftales.conversion.image.Image
import com.github.neftales.conversion.textplain.TextPlainToHtml

package object graph {

  implicit val converteGraph = new ConvertGraph
  converteGraph(List("TXT", "HTML", "PNG", "TIFF", "GIF", "BMP", "JPEG"))

  converteGraph.connectWithWeightWithBehavior("TXT", List(("HTML", 1, TextPlainToHtml)))
  converteGraph.connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  converteGraph.connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  converteGraph.connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  converteGraph.connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG)))
}
