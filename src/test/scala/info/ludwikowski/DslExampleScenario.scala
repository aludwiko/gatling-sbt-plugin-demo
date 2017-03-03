package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, exec, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DslExampleScenario extends MySimulation {

  val search =  repeat(5, "i") {
    exec(http("Page ${i}")
      .get("/computers?p=${i}"))
      .pause(1)
  }

  val complexScenario = scenario("Complex demo scenario").exec(search)

  setUp(complexScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
