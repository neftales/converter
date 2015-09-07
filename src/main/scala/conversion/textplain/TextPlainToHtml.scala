package conversion.textplain

import java.io.File

import com.gilt.handlebars.scala.Handlebars
import com.gilt.handlebars.scala.binding.dynamic._

case object TextPlainToHtml extends Function[Seq[Byte], Seq[Byte]] {
  def apply(file: Seq[Byte]): Seq[Byte] = {
    val path = getClass.getClassLoader.getResource("templateHTML.html").getPath
    val template = new File(path)
    val t = Handlebars(template)
    val map = Map("body" -> readSeq(file))
    val textoSaida = t(map)

    textoSaida.getBytes.toSeq
  }


}

