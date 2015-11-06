package com.github.neftales.graph

import java.io.{File, FileInputStream}

import com.github.neftales.api.ConversionUtils
import org.scalatest.{FlatSpec, Matchers}

class ConversionUtilsSpec extends FlatSpec with Matchers {

  val filePNG = new File(getClass.getClassLoader.getResource("docker.png").getPath)

  "PNG" should "be conveted in GIF file" in {
    val fileIn = new FileInputStream(filePNG)
    val bytes = new Array[Byte](fileIn.available)
    fileIn.read(bytes)

    ConversionUtils.getConversion("PNG", "GIF") match {
      case Some(conversion) =>
        val file = conversion(bytes.toSeq)
        file.nonEmpty shouldBe true
      case _ => fail("Não há conversão.")
    }

  }
}
