package com.github.neftales.conversion.image

import com.typesafe.scalalogging.LazyLogging


object Image extends LazyLogging {

  object toBMP extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      logger.info(s" -> BMP")
      writeTo(file, "bmp")
    }
  }

  object toGIF extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      logger.info(s" -> GIF")
      writeTo(file, "gif")
    }
  }

  object toJPEG extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      logger.info(s" -> JPEG")
      writeTo(file, "jpg")
    }
  }

  object toPNG extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      logger.info(s" -> PNG")
      writeTo(file, "png")
    }
  }

  object toTIFF extends Function[Seq[Byte], Seq[Byte]] {
    override def apply(file: Seq[Byte]): Seq[Byte] = {
      logger.info(s" -> TIFF")
      writeTo(file, "tiff")
    }
  }

}
