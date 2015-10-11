package graph

import scala.collection.mutable
import scala.collection.mutable._

class Dijkstra[G <: ConvertGraph](graph: G) {
  type Node = G#Node
  type Edge = G#Edge

  def compute(start: String): Option[(Map[String, Int], Map[String, Node])] = {
    graph.getNode(start) match {
      case Some(x) =>
        val (dis, path) = compute(x)

        val disString = dis map (t => t._1.toString -> t._2)
        val pathString = path map (t => t._1.toString -> t._2)
        Some(disString, pathString)
      case _ => None
    }

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

  private def initDistance(nodes: List[Node]) = {
    nodes.map(t => t -> Int.MaxValue)(collection.breakOut): Map[Node, Int]
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

  def mountListOfEdge(start: Node, end: Node): Option[List[Edge]] = {
    val (dis, path) = compute(start)

    val stack = new mutable.Stack[Node]()
    stack.push(end)

    try {
      while (stack.top != start) {
        val node = path(stack.top)
        stack.push(node)
      }
      mountListOfEdge(stack)
    } catch {
      case e: NoSuchElementException => None
    }
  }

  def mountListOfEdge(stack: mutable.Stack[Node]): Option[List[Edge]] = {
    val list = new mutable.MutableList[Edge]

    while (stack.size > 1) {
      val node = stack.pop()
      graph.getEdge(node.toString, stack.top.toString) match {
        case Some(edge) => list += edge
        case _ => None
      }
    }

    Some(list.toList)
  }
}