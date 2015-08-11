package graph

import conversion.Converter
import conversion.conversion.txt.TxtToHtml

object Hello {

val graph: Map[String, List[Converter]] = Map("Html" -> List(TxtToHtml))
  def main(args: Array[String]): Unit = {
    println(graph)
  }
}
