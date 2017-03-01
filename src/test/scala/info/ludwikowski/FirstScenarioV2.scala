package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, scenario, _}
import io.gatling.http.Predef.http

class FirstScenarioV2 extends MySimulation{

  val firstScenario = scenario("First Scenario Name")
    .exec(http("Request name").get("/"))
    .pause(7)

  setUp(firstScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
