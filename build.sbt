ThisBuild / scalaVersion := "2.12.11"
ThisBuild / organization := "io.github.daenyth"
ThisBuild / organizationName := "Daenyth"

val commonSettings = Seq(
  addCompilerPlugin(CompilerPlugins.`better-monadic-for`),
  addCompilerPlugin(CompilerPlugins.`kind-projector`),
  parallelExecution in Test := true,
  Compile / doc / scalacOptions -= "-Xfatal-warnings",
)

lazy val core = (project in file("modules/basilisk-core")).settings(
  commonSettings,
  name := "basilisk-core",
  libraryDependencies ++= Dependencies.core,
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "root",
  )
  .enablePlugins(ScalaUnidocPlugin)
  .aggregate(core)

//// Jar publishing settings
// Don't publish the "root" project, only the modules
publish / skip := true

ThisBuild / Test / testOptions ++= {
  if (sys.env.get("CI").contains("true"))
    // format: off
    Seq(Tests.Argument(TestFrameworks.ScalaTest,
      "-oWD",
      "-u", "target/test-reports",
      // "-h", "target/test-html-report", // Disabled for now
      "-W", "120", "60",
    )) // format: on
  else Seq.empty
}
