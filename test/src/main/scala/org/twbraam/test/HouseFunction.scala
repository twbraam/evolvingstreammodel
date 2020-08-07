package org.twbraam.test

import ml.dmlc.xgboost4j.scala.{Booster, DMatrix, XGBoost}
import org.apache.flink.api.common.functions.MapFunction
import org.twbraam.test.housesource.House

class HouseFunction extends MapFunction[House, Float] {

  val modelStartTime: Long = System.currentTimeMillis
  val booster: Booster = XGBoost.loadModel("src/main/resources/model.json")
  println(s"Model loaded: $booster -> ${System.currentTimeMillis - modelStartTime}")

  override def map(house: House): Float = {
    val arr = house.data.split(",").map(x => if (x.isEmpty) "0" else x).map(_.toFloat)
    val vector = new DMatrix(arr, 1, 262)

    val predictionStartTime = System.currentTimeMillis
    val res = booster.predict(vector)
    println(s"Finished prediction1 -> ${System.currentTimeMillis - predictionStartTime}")

    res.head.head
  }
}
