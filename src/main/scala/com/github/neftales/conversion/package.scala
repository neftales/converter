package com.github.neftales

import java.io.{FileOutputStream, FileInputStream, File}

package object conversion {

  def readFileToByte(file: File): Array[Byte] = {
    val fileInputStream = new FileInputStream(file)
    val bytes = new Array[Byte](fileInputStream.available())
    fileInputStream.read(bytes)
    bytes
  }

  def getResourceFile(name: String): File = {
    val resource = getClass.getClassLoader.getResource(name).getPath
    new File(resource)
  }

  def writeToFile(name :String, file: Seq[Byte]) {
    val fileOutputStream = new FileOutputStream(name)
    fileOutputStream.write(file.toArray)
    fileOutputStream.flush
    fileOutputStream.close
  }
}
