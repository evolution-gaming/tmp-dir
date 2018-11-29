package com.evolutiongaming.tmpdir

import java.nio.file.{Files, Path}

import com.evolutiongaming.file.PathHelper._

trait TmpDir {
  def path: Path
  def delete(): Unit

  override def toString: String = s"TmpDir($path)"
}

object TmpDir {

  private lazy val deleteOnExit = {
    var dirs = List.empty[TmpDir]
    sys.addShutdownHook {
      dirs.foreach(_.delete())
    }
    dir: TmpDir =>
      TmpDir.synchronized {
        dirs = dir :: dirs
      }
  }

  def apply(name: String, deleteOnExit: Boolean = true): TmpDir = {
    val dir = Files.createTempDirectory(name)
    Files.createDirectories(dir)
    val tmpDir = apply(dir)
    if (deleteOnExit) this.deleteOnExit(tmpDir)
    tmpDir
  }

  private def apply(dir: Path): TmpDir = new TmpDir {
    def path = dir
    def delete() = path.deleteRecursively()
  }
}
