package info.ludwikowski

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef.http

abstract class MySimulation extends Simulation {

  val httpConf = http
    .baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val goToRootPage = http("root page").get("/")

  def searchFor(query: String) = http("search for").get("/computers?f=" + query)

  def positionAt(index: Int) = http("position at").get("/computers/" + index)

  def goToPage(pageNr: Int) = http("got to page").get("/computers?p=" + pageNr)

  val openNewComputerForm = http("open new computer form").get("/computers/new")

  val addNewComputer = http("add new computer")
    .post("/computers")
    .formParam("name", "Beautiful Computer")
    .formParam("introduced", "2012-05-30")
    .formParam("discontinued", "")
    .formParam("company", "37")
}