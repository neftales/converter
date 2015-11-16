package com.github.neftales.controllers

case class RequestConversion (start: String, end: String, content: Array[Byte])

case class ResponseConversion (format: String, content: String)

case class NodeResponse(format: String, cost: Int)
