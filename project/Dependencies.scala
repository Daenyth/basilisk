import sbt._

object Libraries {

  object Version {
    val cats = "2.1.1"
    val `cats-effect` = "2.1.3"
    val circe = "0.13.0"
    val diffx = "0.3.28"
    val fs2 = "2.2.1"
    val log4cats = "1.1.1"
    val scalatest = "3.1.1"
  }

  val `cats-core` = "org.typelevel" %% "cats-core" % Version.cats
  val `cats-effect` = "org.typelevel" %% "cats-effect" % Version.`cats-effect`
  val `cats-effect-laws` = "org.typelevel" %% "cats-effect-laws" % Version.`cats-effect`
  val `cats-kernel` = "org.typelevel" %% "cats-kernel" % Version.cats
  val `cats-laws` = "org.typelevel" %% "cats-laws" % Version.cats
  val `cats-scalacheck` = "io.chrisdavenport" %% "cats-scalacheck" % "0.2.0"
  val `circe-core` = "io.circe" %% "circe-core" % Version.circe

  val diffx = "com.softwaremill.diffx" %% "diffx-scalatest" % Version.diffx
  val discipline = "org.typelevel" %% "discipline-core" % "1.0.0"
  val `discipline-scalatest` = "org.typelevel" %% "discipline-scalatest" % "1.0.1"

  val fs2 = "co.fs2" %% "fs2-core" % Version.fs2

  val `log4cats-core` = "io.chrisdavenport" %% "log4cats-core" % Version.log4cats
  val `log4cats-slf4j` = "io.chrisdavenport" %% "log4cats-slf4j" % Version.log4cats

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalatest
  val scalacheck = "org.scalacheck" %% "scalacheck" % "1.14.3"
  val scalactic = "org.scalactic" %% "scalactic" % Version.scalatest
  val scalatest = "org.scalatest" %% "scalatest" % Version.scalatest
  val `scalatestplus-scalacheck` = "org.scalatestplus" %% "scalacheck-1-14" % "3.1.1.1"
  val shapeless = "com.chuusai" %% "shapeless" % "2.3.3"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.7.30"

}

/** Compiler plugins are used in build.sbt; they're distinct from sbt plugins. **/
object CompilerPlugins {
  lazy val `better-monadic-for` = "com.olegpy" %% "better-monadic-for" % "0.3.1"

  lazy val `kind-projector` =
    "org.typelevel" % "kind-projector" % "0.10.3" cross CrossVersion.binary
}

object Dependencies {
  private val L = Libraries // For convenience

  private val testing = Seq(
    L.`cats-scalacheck`,
    L.diffx,
    L.scalacheck,
    L.scalactic,
    L.scalatest,
    L.`scalatestplus-scalacheck`,
    L.`cats-effect-laws`,
    L.`cats-laws`,
    L.discipline,
    L.`discipline-scalatest`
  )

  val core =
    testing.map(_ % Test) ++ Seq(
      L.`cats-core`,
      L.`cats-effect`,
      L.`cats-kernel`,
      L.`circe-core`,
      L.fs2,
      L.`log4cats-core`,
      L.`log4cats-slf4j`,
      L.shapeless,
      L.slf4j,
    )

}
