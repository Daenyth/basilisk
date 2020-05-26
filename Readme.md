# Basilisk card game engine

An engine to drive virtual versions of tabletop card games.

The goal of this project is to provide a foundation for data-driven card game applications

## Using this library

Add the following to your `build.sbt`:

```scala
libraryDependencies += "io.github.daenyth" %% "basilisk-core" % version
```

## View the api documentation locally

Run `sbt unidoc` and then open [target/scala-2.13/unidoc/basilisk/index.html](target/scala-2.13/unidoc/basilisk/index.html)

## Testing

Run `sbt test`

### Coverage

Run `sbt coverage test` to add coverage
Run `sbt coverageAggregate` to prepare the coverage report
