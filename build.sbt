import Libraries.libs

ThisBuild / scalaVersion := "2.13.2"
ThisBuild / organization := "io.github.daenyth"
ThisBuild / organizationName := "Daenyth"

val commonSettings =
  addCompilerPlugins(libs, "better-monadic-for", "kind-projector") ++ Seq(
    // addCompilerPlugin(CompilerPlugins.`better-monadic-for`),
    // addCompilerPlugin(CompilerPlugins.`kind-projector`),
    parallelExecution in Test := true,
    scalacOptions += "-Ymacro-annotations",
    Compile / doc / scalacOptions -= "-Xfatal-warnings",
  )

lazy val core = (project in file("modules/core")).settings(
  commonSettings,
  name := "basilisk-core",
  libs.dependencies("fs2-core"),
  libs.testDependencies("munit", "cats-effect-laws", "discipline-core"),
)

lazy val `game-data` = (project in file("modules/game-data"))
  .settings(
    commonSettings,
    name := "basilisk-game-data",
    libraryDependencies ++= Dependencies.`game-data`
  )
  .dependsOn(core)

lazy val engine = (project in file("modules/engine"))
  .settings(commonSettings,
            name := "basilisk-engine",
            libraryDependencies ++= Dependencies.engine,
            libs.dependencies("cats-stm", "monocle-core", "monocle-macro"),
  )
  .dependsOn(core, `game-data`)

lazy val example = (project in file("modules/example"))
  .settings(commonSettings,
            name := "basilisk-example",
            libraryDependencies ++= Dependencies.example,
            publish / skip := true,
            libs.testDependencies("munit"),
  )
  .dependsOn(engine, `game-data`)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "root",
  )
  .enablePlugins(ScalaUnidocPlugin)
  .aggregate(core, `game-data`, engine, example)

//// Jar publishing settings
// Don't publish the "root" project, only the modules
publish / skip := true

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

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
