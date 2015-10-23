package com.github.neftales.conversion

import java.io.ByteArrayInputStream

package object textplain {

  def readSeq(seq: Seq[Byte]): String = {
    val byteArray = seq.toArray
    val file = new ByteArrayInputStream(byteArray)
    file.read
    new String(byteArray)
  }
}
