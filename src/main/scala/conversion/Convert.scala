package conversion

abstract class Converter(weight: Int) extends Function[Seq[Byte], Seq[Byte]]

