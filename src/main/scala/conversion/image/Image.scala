package conversion.image

object Image {

  object toBMP extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      writeTo(file, "bmp")
    }
  }

  object toGIF extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      writeTo(file, "gif")
    }
  }

  object toJPEG extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      writeTo(file, "jpg")
    }
  }

  object toPNG extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      writeTo(file, "png")
    }
  }

  object toTIFF extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      writeTo(file, "tiff")
    }
  }

}
