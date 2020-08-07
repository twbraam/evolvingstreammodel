package org.twbraam.test

import org.apache.flink.streaming.api.scala.function.{ProcessAllWindowFunction, ProcessWindowFunction}
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import org.twbraam.test.housesource.House

class HouseWindowFunction extends ProcessAllWindowFunction[House, String, TimeWindow] {

  def process(context: Context, input: Iterable[House], out: Collector[String]): Unit = {
    val houses: Iterable[House] = for {
      in <- input
    } yield in
    out.collect(s"Window ${context.window} houses: $houses")
  }
}