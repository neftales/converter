import graph.{ConvertGraph, Graph}

import scala.collection.mutable._

class Dijkstra[G <: ConvertGraph](graph: G) {
  type Node = G#Node
  type Edge = G#Edge

  def compute(start: Node, target: Node): (Map[Node, Int], Map[Node, Node]) = {
    var queue: Set[Node] = new HashSet()
    var settled: Set[Node] = new HashSet()
    var distance: Map[Node, Int] = new HashMap()
    var path: Map[Node, Node] = new HashMap()

    queue += start
    distance(start) = 0

    while (!queue.isEmpty) {
      val u = extractMinimum(queue, distance)
      settled += u
      relaxNeighbors(u, queue, settled, distance, path)
    }

    (distance, path)
  }

  protected def extractMinimum[T](Q: Set[T], D: Map[T, Int]): T = {
    var u = Q.head
    Q.foreach((node) => if (D(u) > D(node)) u = node)
    Q -= u
    u
  }

  protected def relaxNeighbors(u: Node, Q: Set[Node], S: Set[Node],
                               D: Map[Node, Int], P: Map[Node, Node]) = {
    for (edge <- graph.edges filter (edge => edge.a == u)) {
      var v = edge.b
      if (!S.contains(v)) {
        if (!D.contains(v) || D(v) > D(u) + edge.getWeight) {
          D(v) = D(u) + edge.getWeight
          P(v) = u
          Q += v
        }
      }
    }
  }
}