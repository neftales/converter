package conversion

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import javax.imageio.ImageIO

package object image {
  def writeTo(file: Seq[Byte], format: String): Seq[Byte] = {
    val img = read(file)
    val newImg = new BufferedImage(img.getWidth, img.getHeight, BufferedImage.TYPE_INT_RGB)
    newImg.createGraphics().drawImage(img, 0, 0, Color.WHITE, null)
    val arrayOutputStream = new ByteArrayOutputStream()

    ImageIO.write(newImg, format, arrayOutputStream)
    arrayOutputStream.toByteArray.toSeq
  }

  def read(bytes: Seq[Byte]): BufferedImage = {
    val arrayBytes = bytes.toArray
    val stream = new ByteArrayInputStream(arrayBytes)

    ImageIO.read(stream)
  }

}
