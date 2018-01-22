package dropwizard

import java.util.concurrent.TimeUnit
import java.util.function.Consumer

import com.codahale.metrics.Timer
import org.HdrHistogram.{Histogram, HistogramIterationValue}

import scala.util.Random

object DropWizardTest extends App {
  val random = Random

//  val times = Seq(10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20,10,20,30,10,20,10,20)
//
  val times =
//    //normal behavior
    (1 to 100000).map(_ => random.nextInt(100)) ++
//    //standard picks
      (1 to 100).map(_ => 1000) ++
      (1 to 10).map(_ => 1500) ++
//    //very high picks
      (1 to 10).map(_ => 10000)

  //print histogram for the same data set
  (1 to 1).foreach(_ => printHdrHistogram(times))

  def printHistogram(times: Seq[Int]) = {
    val timer = new Timer
    times.map(time => timer.update(time, TimeUnit.NANOSECONDS))
    println("-----------")
    printf("75: %f\n", timer.getSnapshot.get75thPercentile())
    printf("95: %f\n", timer.getSnapshot.get95thPercentile())
    printf("99: %f\n", timer.getSnapshot.get99thPercentile())
    printf("99.9: %f\n", timer.getSnapshot.get999thPercentile())
    printf("max: %d\n", timer.getSnapshot.getMax)
  }

  def printHdrHistogram(times: Seq[Int]) = {
    val histogram = new Histogram(1000000, 0)
    times.map(time => histogram.recordValue(time))
    histogram.outputPercentileDistribution(System.out, 1, 1.0)
  }
}
