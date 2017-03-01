package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, scenario, _}
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class ComplexScenario extends MySimulation{

  val complexScenario = scenario("Scenario Name")
    .exec(http("request_1").get("/"))
    .pause(7)
    .exec(http("request_2").get("/computers?f=macbook"))
    .pause(2)
    .exec(http("request_3").get("/computers/6"))
    .pause(3)
    .exec(http("request_4").get("/"))
    .pause(2)
    .exec(http("request_5").get("/computers?p=1"))
    .pause(670 milliseconds)
    .exec(http("request_6").get("/computers/new"))
    .pause(1)
    .exec(http("request_7")
      .post("/computers")
      .formParam("name", "Beautiful Computer")
      .formParam("introduced", "2012-05-30")
      .formParam("discontinued", "")
      .formParam("company", "37"))

  setUp(complexScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
