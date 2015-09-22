package graph

import java.io.{FileOutputStream, File, FileInputStream}

import conversion.image.Image
import conversion.textplain.TextPlainToHtml
import org.scalatest.{FlatSpec, Matchers}

class ConversionUtilsSpec extends FlatSpec with Matchers {

  val filePNG = new File(getClass.getClassLoader.getResource("docker.png").getPath)

  val graph = new ConvertGraph
  graph(List("TXT", "HTML", "PNG", "TIFF", "GIF", "BMP", "JPEG"))
  graph connectWithWeightWithBehavior("TXT", List(("HTML", 1, TextPlainToHtml)))
  graph connectWithWeightWithBehavior("PNG", List(("TIFF", 5, Image.toTIFF), ("JPEG", 1, Image.toJPEG)))
  graph connectWithWeightWithBehavior("JPEG", List(("BMP", 5, Image.toBMP)))
  graph connectWithWeightWithBehavior("BMP", List(("GIF", 3, Image.toGIF)))
  graph connectWithWeightWithBehavior("GIF", List(("PNG", 1, Image.toPNG)))

  "PNG" should "be conveted in GIF file" in {
    val conversion = new ConversionUtils().getConversion("PNG", "GIF", graph)
    val fileIn = new FileInputStream(filePNG)
    val bytes = new Array[Byte](fileIn.available)

    fileIn.read(bytes)

    val file = conversion(bytes.toSeq)

    val fileOut = new FileOutputStream("I:\\Teste\\A.gif")
    fileOut.write(file.toArray)
    fileOut.flush()
    fileOut.close()
  }
}
