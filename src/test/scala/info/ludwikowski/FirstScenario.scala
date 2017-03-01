package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, scenario, _}
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef.http

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val firstScenario = scenario("First Scenario Name")
    .exec(http("Request name").get("/"))
    .pause(7)

  setUp(firstScenario.inject(atOnceUsers(1)).protocols(httpConf))
}

