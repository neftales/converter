package graph

import scala.collection.mutable._

class Dijkstra[G <: ConvertGraph](graph: G) {
  type Node = G#Node
  type Edge = G#Edge

  private def initDistance(nodes: List[Node]) = {
    nodes.map (t => t -> Int.MaxValue)(collection.breakOut): Map[Node, Int]
  }

  def compute(start: String): (Map[String, Int], Map[String, Node]) = {
    val node = graph.getNode(start)
    val (dis, path) = compute(node)

    val disString = dis map(t => t._1.toString -> t._2)
    val pathString = path map(t => t._1.toString -> t._2)
    (disString, pathString)
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