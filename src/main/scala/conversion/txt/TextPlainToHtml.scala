package conversion.txt

import conversion.Converter

case object TextPlainToHtml extends Converter(1) {
  def apply(bytes: Seq[Byte]): Seq[Byte] = {
    ???
  }

  override def toString(): String = {
    "Html"
  }
}

