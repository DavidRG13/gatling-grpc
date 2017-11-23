name := "gatling-grpc"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
    "io.gatling" % "gatling-core" % "2.3.0",
    "com.google.protobuf" % "protobuf-java" % "3.4.0"
)