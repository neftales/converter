package com.github.neftales

import com.github.neftales.conversion.image.Image

package object graph {

  implicit val converteGraph = new ConvertGraph
  converteGraph(List("PNG", "TIFF", "GIF", "BMP", "JPEG"))

  converteGraph.connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  converteGraph.connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  converteGraph.connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  converteGraph.connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG), ("JPEG", 3, Image.toJPEG)))
}
