
lazy val root = (project in file(".")).
  settings(
    name := "scala-benchmarking",
    version := "1.0",
//    scalaVersion := "2.10.6"
    scalaVersion := "2.11.7"
  )

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.7"

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

parallelExecution in Test := false
