import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "bazaar",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "bazaar",
    libraryDependencies ++= Seq (
      scalaTest % Test,
      "org.postgresql" % "postgresql" % "42.0.0"
      ),
    unmanagedResourceDirectories in Test ++= (unmanagedResourceDirectories in Compile).value
  )
