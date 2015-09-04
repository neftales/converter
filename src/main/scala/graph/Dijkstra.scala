package graph

import scala.collection.mutable._

class Dijkstra[G <: ConvertGraph](graph: G) {
  type Node = G#Node
  type Edge = G#Edge

  def initDistance(nodes: List[Node]) = {
    val distance = new HashMap[Node, Int]
    nodes foreach  (x => distance(x) = Int.MaxValue)
    distance
  }

  def compute(start: Node): (Map[Node, Int], Map[Node, Node]) = {
    val queue: Set[Node] = new HashSet
    val settled: Set[Node] = new HashSet
    val distance: Map[Node, Int] = initDistance(graph.nodes)
    val path: Map[Node, Node] = new HashMap

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
      val v = edge.b
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