enablePlugins(GatlingPlugin)

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.3" % "test,it"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.3" % "test,it"

// https://mvnrepository.com/artifact/io.dropwizard.metrics/metrics-core
libraryDependencies += "io.dropwizard.metrics" % "metrics-core" % "3.1.0"

libraryDependencies += "org.hdrhistogram" % "HdrHistogram" % "2.1.10"