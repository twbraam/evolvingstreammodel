package org.twbraam.predictor

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.streaming.api.datastream.{BroadcastStream, DataStream}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.twbraam.predictor.house.HouseSource
import org.twbraam.predictor.modelsignal.{ModelSignalSource, ModelSignalToInt}

object PredictorJob {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val houses: DataStream[String] = env
      .addSource(HouseSource.Predef)
      .name("houses")

    val modelSignals: BroadcastStream[Int] = env
      .addSource(ModelSignalSource.Predef)
      .map(new ModelSignalToInt)
      .broadcast()

    val connected = houses
      .connect(modelSignals)
      .process(new HouseBroadcastFunction)
      .print

    env.execute("Evolving Stream Model Predictor")
  }
}
