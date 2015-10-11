package graph

import java.io.{File, FileInputStream}

import org.scalatest.{FlatSpec, Matchers}

class ConversionUtilsSpec extends FlatSpec with Matchers {

  val filePNG = new File(getClass.getClassLoader.getResource("docker.png").getPath)

  "PNG" should "be conveted in GIF file" in {
    val conversion = ConversionUtils.getConversion("PNG", "GIF")
    val fileIn = new FileInputStream(filePNG)
    val bytes = new Array[Byte](fileIn.available)

    fileIn.read(bytes)
    val file = conversion(bytes.toSeq)

    file.nonEmpty shouldBe true
  }
}
