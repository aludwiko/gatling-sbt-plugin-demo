package info.ludwikowski

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ComplexScenarioV3 extends MySimulation {

  val search = exec(goToRootPage)
    .pause(7)
    .exec(searchFor("macbook"))
    .pause(2)
    .exec(positionAt(6))
    .pause(3)

  val addComputer = exec(goToRootPage)
    .pause(2)
    .exec(goToPage(1))
    .pause(670 milliseconds)
    .exec(openNewComputerForm)
    .pause(1)
    .exec(addNewComputer)

  val complexScenario = scenario("Complex demo scenario").exec(search, addComputer)

  setUp(complexScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
