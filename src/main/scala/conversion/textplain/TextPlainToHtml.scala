package conversion.textplain

case object TextPlainToHtml extends Function[Seq[Byte], Seq[Byte]]{
  def apply(file: Seq[Byte]): Seq[Byte] = ???
}

