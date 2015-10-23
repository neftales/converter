package com.github.neftales.graph


abstract class Graph {
  type Edge <: IEdge
  type Node <: INode

  abstract class INode {
    def connectWith(node: Node): Edge

    def connectWith(label: String): Edge
  }

  abstract class IEdge {
    def a: Node

    def b: Node
  }


  def getEdge(a: Node, b: Node): Option[Edge] = {
    edges.find(edge => edge.a == a && edge.b == b)
  }

  def getEdge(a: String, b: String): Option[Edge] = {
    val nodeA = getNode(a)
    val nodeB = getNode(b)


    if (nodeA == None || nodeB == None) None
    else getEdge(nodeA.get, nodeB.get)
  }

  def getNode(label: String): Option[Node] = {
    nodes.find(node => node.toString.equals(label))
  }

  def connectWith(label: String, nodes: List[String]): List[Edge] = {
    val node = getNode(label).get
    nodes map (x => node.connectWith(x))
  }

  def connectWith(node: Node, nodes: List[Node]): List[Edge] = {
    nodes map (x => node.connectWith(x))
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

    override def connectWith(label: String): Edge = {
      val node = getNode(label).get
      connectWith(node)
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
  def apply(nodes: List[String]) = {
    nodes map (node => addNode(node))
  }

  override type Node = NodeImpl
  override type Edge = EdgeImpl with Weight with Convert

  override protected def newEdge(one: Node, other: Node): Edge with Weight with Convert =
    new EdgeImpl(one, other) with Weight with Convert

  override protected def newNode(label: String): Node = new NodeImpl(label)

  def connectWithWeight(label: String, nodes: List[(String, Int)]): List[Edge] = {
    val node = getNode(label).get
    val nodesList = nodes map { node =>
      getNode(node._1).get -> node._2
    }
    connectWithWeight(node, nodesList)
  }

  def connectWithWeight(node: Node, nodes: List[(Node, Int)]): List[Edge] = {
    nodes.map { x =>
      val edge = node.connectWith(x._1)
      edge.setWeight(x._2)
      edge
    }
  }

  def connectWithWeightWithBehavior(label: String, nodes: List[(String, Int, Function[Seq[Byte], Seq[Byte]])]): List[Edge] = {
    val node = getNode(label).get
    val nodesList = nodes map { node =>
      (getNode(node._1).get, node._2, node._3)
    }
    connectWithWeightWithBehavior(node, nodesList)
  }

  def connectWithWeightWithBehavior(node: Node, nodes: List[(Node, Int, Function[Seq[Byte], Seq[Byte]])]): List[Edge] = {
    nodes.map { x =>
      val edge = node.connectWith(x._1)
      edge.setWeight(x._2)
      edge.setBehavior(x._3)
      edge
    }
  }

}