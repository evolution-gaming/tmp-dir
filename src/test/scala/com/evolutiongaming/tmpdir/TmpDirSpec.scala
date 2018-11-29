package com.evolutiongaming.tmpdir

import java.nio.file.Files

import com.evolutiongaming.file.PathHelper._
import org.scalatest.{FunSuite, Matchers}

class TmpDirSpec extends FunSuite with Matchers {

  test("create and delete tmp dir") {
    val tmpDir = createTmpDir()
    tmpDir.path.exists() shouldEqual true
    tmpDir.delete()
    tmpDir.path.exists() shouldEqual false
  }

  test("create and delete tmp dir on exit") {
    val tmpDir = createTmpDir()
    tmpDir.path.exists() shouldEqual true
  }

  private def createTmpDir() = {
    val dir = TmpDir("tmp-dir")
    val file = dir.path.resolve("test.bin")
    Files.write(file, "test".getBytes("UTF-8"))
    dir
  }
}
