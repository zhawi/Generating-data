ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "capstone_solo",

    libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0",
    libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"
  )
