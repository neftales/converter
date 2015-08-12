package graph

import conversion.Converter
import conversion.txt.{TextPlainToPdf, TextPlainToHtml}

object Graph {
  val graph: Map[String, List[Converter]] = Map("Txt" -> List(TextPlainToHtml, TextPlainToPdf))

  def findConvertionForMinimalPath(in: String, out: String): List[Converter] = {
    ???
  }

  def exictsConvertion(in: String, out: String): Boolean = {
    findConvertionForMinimalPath(in, out).nonEmpty
  }
}

