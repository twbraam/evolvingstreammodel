package org.twbraam.test

import biz.k11i.xgboost.Predictor
import biz.k11i.xgboost.util.FVec
import ml.dmlc.xgboost4j.scala.{DMatrix, XGBoost}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

object Test extends App {
  // first
  val bufferedSource = io.Source.fromFile("src/main/resources/preprocessed.csv")
  val input: Array[Array[Float]] = bufferedSource.getLines.map { line =>
    line.split(",").map(_.trim).map(x => if (x.isEmpty) "0" else x).map(_.toFloat)
  }.toArray.take(10)
  bufferedSource.close




  val price: Array[Float] = input.map(x => x(47))
  val nrows = input.length
  val flatArray: Array[Float] = input.flatMap(x => x.drop(47))

  val modelStartTime1 = System.currentTimeMillis
  val booster = XGBoost.loadModel("src/main/resources/model.json")
  println(s"Model loaded: $booster -> ${System.currentTimeMillis - modelStartTime1}")

  val matrix = new DMatrix(flatArray, nrows, 262)
  println(price.mkString)

  val predictionStartTime1 = System.currentTimeMillis
  val res = booster.predict(matrix)
  println(s"Finished prediction1 -> ${System.currentTimeMillis - predictionStartTime1}")
  res.zip(price).foreach { case (pred, price) => println(price, pred.mkString(", ")) }

}
