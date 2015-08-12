package graph

import conversion.Converter
import conversion.txt.{TextPlainToHtml, TextPlainToPdf}

object Graph {
  val graph: Map[String, List[Converter]] = Map("Txt" -> List(TextPlainToHtml, TextPlainToPdf))

  def findConvertionForMinimalPath(in: String, out: String): Option[List[Converter]] = {
    ???
  }

  def exictsConvertion(in: String, out: String): Boolean = {
    findConvertionForMinimalPath(in, out).nonEmpty
  }
}

