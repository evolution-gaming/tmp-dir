package com.evolutiongaming.file

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files, Path, SimpleFileVisitor}

object PathHelper {

  implicit class PathOps(val self: Path) extends AnyVal {

    def deleteRecursively(): Unit = {

      val visitor = new SimpleFileVisitor[Path]() {

        override def postVisitDirectory(dir: Path, exc: IOException) = {
          Files.deleteIfExists(dir)
          super.postVisitDirectory(dir, exc)
        }

        override def visitFile(file: Path, attrs: BasicFileAttributes) = {
          Files.deleteIfExists(file)
          super.visitFile(file, attrs)
        }
      }

      if (self.exists()) Files.walkFileTree(self, visitor)
    }

    def exists(): Boolean = Files.exists(self)
  }
}
