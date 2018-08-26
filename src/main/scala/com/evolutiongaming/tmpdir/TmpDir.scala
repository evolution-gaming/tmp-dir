package com.evolutiongaming.tmpdir

import java.io.File

trait TmpDir {
  def file: File
  def delete(): Unit

  override def toString: String = s"TmpDir(${ file.getAbsolutePath })"
}

object TmpDir {

  def apply(name: String): TmpDir = new TmpDir {
    val file: File = {
      val file = File.createTempFile(name, null)
      require(file.delete(), s"cannot delete $file")
      require(file.mkdirs(), s"cannot mkdirs $file")
      file.deleteOnExit()
      file
    }

    def delete(): Unit = file.deleteRecursively()
  }

  implicit class FileOps(val self: File) extends AnyVal {

    def deleteRecursively(): Unit = {
      if (self.isDirectory) {
        val files = Option(self.listFiles())
        for {
          files <- files
          file <- files
        } file.deleteRecursively()
      }
      self.delete()
    }
  }
}
