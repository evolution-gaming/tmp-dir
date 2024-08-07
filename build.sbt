name := "tmp-dir"

organization := "com.evolutiongaming"

homepage := Some(url("https://github.com/evolution-gaming/tmp-dir"))

startYear := Some(2018)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.10", "2.12.17", "3.2.1")

publishTo := Some(Resolver.evolutionReleases)

scalacOptions in(Compile, doc) ++= Seq("-groups", "-implicits", "-no-link-warnings")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

releaseCrossBuild := true

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "show version")
addCommandAlias("build", "+all compile test")
