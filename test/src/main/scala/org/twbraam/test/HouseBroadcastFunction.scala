package org.twbraam.test

import ml.dmlc.xgboost4j.scala.{Booster, DMatrix, XGBoost}
import org.apache.flink.api.common.state.{BroadcastState, MapStateDescriptor}
import org.apache.flink.api.common.typeinfo.{BasicTypeInfo, TypeHint, TypeInformation}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction
import org.apache.flink.util.Collector
import org.twbraam.test.housesource.House
import org.twbraam.test.modelsignal.ModelSignal

import scala.collection.JavaConverters._

class HouseBroadcastFunction extends BroadcastProcessFunction[House, ModelSignal, Float] {
  var booster: Booster = _
  var boosterIndex: Int = 0
  val ruleStateDescriptor: MapStateDescriptor[Integer, String] = new MapStateDescriptor(
    "RulesBroadcastState", BasicTypeInfo.INT_TYPE_INFO, TypeInformation.of(new TypeHint[String] {}))

  override def open(configuration: Configuration): Unit = {
    val modelStartTime: Long = System.currentTimeMillis
    booster = XGBoost.loadModel("src/main/resources/model.json")

    println(s"Model loaded: $booster, index: $boosterIndex -> ${System.currentTimeMillis - modelStartTime}")
  }

  override def processElement(house: House,
                               readOnlyCtx: BroadcastProcessFunction[House, ModelSignal, Float]#ReadOnlyContext,
                               out: Collector[Float]): Unit = {
    val state = readOnlyCtx.getBroadcastState(ruleStateDescriptor)
    val stateMap = Map(new Integer(0) -> "empty") ++ (for {
      entry <- state.immutableEntries().asScala
    } yield (entry.getKey, entry.getValue)).toMap

    val highestKey = stateMap.keys.max
    if (highestKey > boosterIndex) {
      boosterIndex = highestKey
      open(new Configuration())
    }

    val arr = house.data.split(",").map(x => if (x.isEmpty) "0" else x).map(_.toFloat)
    val vector = new DMatrix(arr, 1, 262)

    val predictionStartTime = System.currentTimeMillis
    val res = booster.predict(vector)
    println(s"Finished prediction -> ${System.currentTimeMillis - predictionStartTime}")

    out.collect(res.head.head)
  }

  override def processBroadcastElement(modelSignal: ModelSignal,
                               ctx: BroadcastProcessFunction[House, ModelSignal, Float]#Context,
                               out: Collector[Float]): Unit = {
    ctx.getBroadcastState(ruleStateDescriptor).put(modelSignal.idx, "hoi")
    println("modelSignal Processed: " + modelSignal)
  }
}