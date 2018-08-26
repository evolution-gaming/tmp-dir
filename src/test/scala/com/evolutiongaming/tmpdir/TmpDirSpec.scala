package com.evolutiongaming.tmpdir

import org.scalatest.{FunSuite, Matchers}

class TmpDirSpec extends FunSuite with Matchers {

  test("create and delete tmp dir") {
    val tmpDir = TmpDir("tmp-dir")
    tmpDir.file.exists() shouldEqual true
    tmpDir.delete()
    tmpDir.file.exists() shouldEqual false
  }
}
