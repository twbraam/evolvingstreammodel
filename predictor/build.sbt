ThisBuild / resolvers ++= Seq(
    "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
    Resolver.mavenLocal
)

name := "Evolving Stream Model Predictor"

version := "0.1-SNAPSHOT"

organization := "org.twbraam.predictor"

ThisBuild / scalaVersion := "2.11.12"

val flinkVersion = "1.11.1"

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-clients"         % flinkVersion % "provided",
  "org.apache.flink" %% "flink-scala"           % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided",
  "org.apache.flink" %% "flink-connector-kafka" % flinkVersion,
  "ml.dmlc"          %% "xgboost4j"             % "1.0.0"
)

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= flinkDependencies
  )

assembly / mainClass := Some("org.twbraam.predictor.PredictorJob")

// make run command include the provided dependencies
Compile / run  := Defaults.runTask(Compile / fullClasspath,
                                   Compile / run / mainClass,
                                   Compile / run / runner
                                  ).evaluated

// stays inside the sbt console when we press "ctrl-c" while a Flink programme executes with "run" or "runMain"
Compile / run / fork := true
Global / cancelable := true

// exclude Scala library from assembly
assembly / assemblyOption  := (assembly / assemblyOption).value.copy(includeScala = false)