package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, exec, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DslExampleScenario extends MySimulation {

  val search =  repeat(5, "i") {
    exec(goToPage("${i}".toInt))
      .pause(1)
  }.doIf(session => session("user").as[String].startsWith("admin")) {
    // executed if the session value stored in "myKey" starts with "admin"
    exec(goToAdminPage)
  }


  val complexScenario = scenario("Complex demo scenario").exec(search)

  setUp(complexScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
