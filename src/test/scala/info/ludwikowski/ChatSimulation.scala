package info.ludwikowski

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef.http

class ChatSimulation extends Simulation {

  val httpConf = http
//    .baseURL("http://localhost:9000") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
    .get("http://localhost:9000/")
    .check(status is 200)
    )
        .exec(ws("ws req").open("ws://localhost:9000/ws"))
    .exec(ws("ws req 1").sendText("asd"))


  setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
