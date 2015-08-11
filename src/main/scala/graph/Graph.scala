package graph

import conversion.Converter
import conversion.conversion.txt.TxtToHtml

object Graph {
  val graph: Map[String, List[Converter]] = Map("Txt" -> List(TxtToHtml))
}
