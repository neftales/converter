package com.github.neftales.conversion.image

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.io.{File, FileInputStream}

class ImageSpec extends AnyFlatSpec with Matchers {

  val fileBMP = new File(getClass.getClassLoader.getResource("docker.bmp").getPath)
  val fileJPEG = new File(getClass.getClassLoader.getResource("docker.jpg").getPath)
  val filePNG = new File(getClass.getClassLoader.getResource("docker.png").getPath)
  val fileGIF = new File(getClass.getClassLoader.getResource("docker.gif").getPath)
  val fileTIFF = new File(getClass.getClassLoader.getResource("docker.tiff").getPath)

  def readFileToByte(file: File): Array[Byte] = {
    val fileInputStream = new FileInputStream(file)
    val bytes = new Array[Byte](fileInputStream.available())
    fileInputStream.read(bytes)
    bytes
  }

  "BMP" should "be converted in JPG" in {
    val bytes = readFileToByte(fileBMP)
    Image.toJPEG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in PNG" in {
    val bytes = readFileToByte(fileBMP)
    Image.toPNG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in GIF" in {
    val bytes = readFileToByte(fileBMP)
    Image.toGIF(bytes).nonEmpty shouldBe true
  }

  it should "be converted in TIFF" in {
    val bytes = readFileToByte(fileBMP)
    Image.toTIFF(bytes).nonEmpty shouldBe true
  }

  "GIF" should "be converted in JPG" in {
    val bytes = readFileToByte(fileGIF)
    Image.toJPEG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in PNG" in {
    val bytes = readFileToByte(fileGIF)
    Image.toPNG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in BMP" in {
    val bytes = readFileToByte(fileGIF)
    Image.toBMP(bytes).nonEmpty shouldBe true
  }

  it should "be converted in TIFF" in {
    val bytes = readFileToByte(fileGIF)
    Image.toTIFF(bytes).nonEmpty shouldBe true
  }

  "JPEG" should "be converted in GIF" in {
    val bytes = readFileToByte(fileJPEG)
    Image.toGIF(bytes).nonEmpty shouldBe true
  }

  it should "be converted in PNG" in {
    val bytes = readFileToByte(fileJPEG)
    Image.toPNG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in BMP" in {
    val bytes = readFileToByte(fileJPEG)
    Image.toBMP(bytes).nonEmpty shouldBe true
  }

  it should "be converted in TIFF" in {
    val bytes = readFileToByte(fileJPEG)
    Image.toTIFF(bytes).nonEmpty shouldBe true
  }

  "PNG" should "be converted in GIF" in {
    val bytes = readFileToByte(filePNG)
    Image.toGIF(bytes).nonEmpty shouldBe true
  }

  it should "be converted in JPEG" in {
    val bytes = readFileToByte(filePNG)
    Image.toJPEG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in BMP" in {
    val bytes = readFileToByte(filePNG)
    Image.toBMP(bytes).nonEmpty shouldBe true
  }

  it should "be converted in TIFF" in {
    val bytes = readFileToByte(filePNG)
    Image.toTIFF(bytes).nonEmpty shouldBe true
  }

  "TIFF" should "be converted in JPEG" in {
    val bytes = readFileToByte(fileTIFF)
    val image = Image.toJPEG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in PNG" in {
    val bytes = readFileToByte(fileTIFF)
    Image.toPNG(bytes).nonEmpty shouldBe true
  }

  it should "be converted in BMP" in {
    val bytes = readFileToByte(fileTIFF)
    Image.toBMP(bytes).nonEmpty shouldBe true
  }

  it should "be converted in GIF" in {
    val bytes = readFileToByte(fileTIFF)
    Image.toGIF(bytes).nonEmpty shouldBe true
  }

}
