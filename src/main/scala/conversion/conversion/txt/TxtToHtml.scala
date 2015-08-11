package conversion.conversion.txt

import conversion.Converter

case object TxtToHtml extends Converter(1000) {
  def apply(bytes: Seq[Byte]): Seq[Byte] = {
    ???
  }

  override def toString(): String = {
    "Html"
  }
}

