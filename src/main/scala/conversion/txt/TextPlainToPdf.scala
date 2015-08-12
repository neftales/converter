package conversion.txt

import conversion.Converter

case object TextPlainToPdf extends Converter(1) {
  def apply(v1: Seq[Byte]): Seq[Byte] = {
    ???
  }

  override def toString(): String = {
    "Pdf"
  }
}
