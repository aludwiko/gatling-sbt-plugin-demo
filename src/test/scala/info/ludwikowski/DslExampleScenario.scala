package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, exec, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class DslExampleScenario extends MySimulation {

  val search = repeat(5, "i") {
    exec(goToPage("${i}".toInt))
      .pause(1)
  }.doIf(session => session("user").as[String].startsWith("admin")) {
      // executed if the session value stored in "myKey" starts with "admin"
      exec(goToAdminPage)
    }
    .exec(sendMoney)
    .tryMax(10) {
      exec(receiveMoney)
    }
    .exec(
      polling
        .every(10 seconds)
        .exec(searchFor("thinkpad"))
    )

  val myScenario = scenario("Complex demo scenario").exec(search)

  setUp(
    myScenario
      .inject(
        nothingFor(4 seconds),
        atOnceUsers(10),
        rampUsers(10) over (5 seconds)
      )
      .protocols(httpConf))
    .assertions(
      global.responseTime.max.lt(50),
      global.failedRequests.percent.is(0)
    )
    .throttle(
      reachRps(100) in (30 second),
      holdFor(1 minute),
      jumpToRps(50),
      holdFor(2 hours)
    )
    .maxDuration(10 minutes)
}
