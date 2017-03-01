package info.ludwikowski

import io.gatling.core.Predef.{atOnceUsers, scenario}
import io.gatling.http.Predef.http
import io.gatling.core.Predef.{atOnceUsers, scenario, _}
import io.gatling.http.Predef.http
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ChecksExample extends MySimulation {

  val firstScenario = scenario("First Scenario Name")
    .exec(
      http("Request name")
        .get("/computers")
        .check(status.is(200))
        .check(status.in(200 to 210))
        .check(regex("computers")
          .find(1)
          .exists)
        .check(jsonPath("$..foo.bar[2].baz")
          .ofType[String]
          .notExists)
        .check(xpath("//input[@id='text1']/@value")
          .is("test"))
        .check(css("...")
          .transform(_.split('|').toSeq)
          .is(Seq("1", "2")))
        //saving token for other request
        .check(regex("token").find(1).exists
          .saveAs("authorizationToken")))
    .exec(http("Authorized resource")
      .get("/authorized_resource?token=${authorizationToken}"))

  setUp(firstScenario.inject(atOnceUsers(1)).protocols(httpConf))
}
