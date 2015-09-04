package graph

abstract class Graph {
  type Edge <: IEdge
  type Node <: INode

  abstract class INode {
    def connectWith(node: Node): Edge
  }

  abstract class IEdge {
    def a: Node

    def b: Node
  }

  def nodes: List[Node]

  def edges: List[Edge]

  def addNode(label: String): Node
}

trait Weight {
  var weight = 1

  def getWeight = weight

  def setWeight(weight: Int): Unit = {
    this.weight = weight
  }
}

trait Convert {
  var behavior = (seq: Seq[Byte]) => seq

  def getBehavior = behavior

  def setBehavior(fun: (Seq[Byte]) => Seq[Byte]): Unit = {
    this.behavior = fun
  }
}

abstract class DirectedGraph extends Graph {

  class EdgeImpl(one: Node, other: Node) extends IEdge {
    def a = one

    def b = other

    override def toString: String = s"$one -> $other"
  }

  class NodeImpl(label: String) extends INode {
    this: Node =>
    def connectWith(node: Node): Edge = {
      val edge = newEdge(this, node)
      edges = edge :: edges
      edge
    }

    override def toString: String = label
  }

  protected def newNode(label: String): Node

  protected def newEdge(one: Node, other: Node): Edge

  var nodes: List[Node] = Nil
  var edges: List[Edge] = Nil

  def addNode(label: String): Node = {
    val node = newNode(label)
    nodes = node :: nodes
    node
  }
}


class ConvertGraph extends DirectedGraph with Weight with Convert {
  override type Node = NodeImpl
  override type Edge = EdgeImpl with Weight with Convert

  override protected def newEdge(one: Node, other: Node): Edge with Weight with Convert =
    new EdgeImpl(one, other) with Weight with Convert

  override protected def newNode(label: String): Node = new NodeImpl(label)
}