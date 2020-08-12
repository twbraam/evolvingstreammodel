package org.twbraam.predictor

import ml.dmlc.xgboost4j.scala.{Booster, DMatrix, XGBoost}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction
import org.apache.flink.util.Collector

class HouseBroadcastFunction extends BroadcastProcessFunction[String, Int, Float] {
  var booster: Booster = _

  override def open(configuration: Configuration): Unit = {
    val modelStartTime: Long = System.currentTimeMillis
    booster = XGBoost.loadModel("src/main/resources/model.json")

    println(s"Model loaded: $booster -> ${System.currentTimeMillis - modelStartTime}")
  }

  override def processElement(house: String,
                              readOnlyCtx: BroadcastProcessFunction[String, Int, Float]#ReadOnlyContext,
                              out: Collector[Float]): Unit = {

    val array = house.split(",").map(x => if (x.isEmpty) "0" else x).map(_.toFloat)
    val vector = new DMatrix(array, 1, 262)

    val predictionStartTime = System.currentTimeMillis
    val res = booster.predict(vector)
    println(s"Finished prediction -> ${System.currentTimeMillis - predictionStartTime}")

    out.collect(res.head.head)
  }

  override def processBroadcastElement(modelSignal: Int,
                               ctx: BroadcastProcessFunction[String, Int, Float]#Context,
                               out: Collector[Float]): Unit = {
    open(new Configuration())
    println("modelSignal Processed: " + modelSignal)
  }
}