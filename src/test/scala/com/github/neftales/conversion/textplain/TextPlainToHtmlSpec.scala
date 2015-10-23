package com.github.neftales.conversion.textplain

import java.io.ByteArrayOutputStream

import org.scalatest.{FlatSpec, Matchers}

class TextPlainToHtmlSpec extends FlatSpec with Matchers {

  trait init {
    val texto = "nefs"
    val out = new ByteArrayOutputStream
    val byteArray = texto.getBytes
    out.write(byteArray)
  }

  "TextPlain" should "be converted in HTML" in new init {
    val html = new String(TextPlainToHtml(byteArray.toSeq).toArray)
    html.contains(texto) shouldBe true
  }
}
