package com.github.neftales.conversion.textplain

case object TextPlainToPdf extends Function[Seq[Byte], Seq[Byte]] {
  def apply(file: Seq[Byte]): Seq[Byte] = ???
}
