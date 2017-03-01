package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, scenario, _}
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class ComplexScenarioV2 extends MySimulation {

  val complexScenario = scenario("Scenario Name")
    .exec(goToRootPage)
    .pause(7)
    .exec(searchFor("macbook"))
    .pause(2)
    .exec(positionAt(6))
    .pause(3)
    .exec(goToRootPage)
    .pause(2)
    .exec(goToPage(1))
    .pause(670 milliseconds)
    .exec(openNewComputerForm)
    .pause(1)
    .exec(addNewComputer)

  setUp(complexScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
