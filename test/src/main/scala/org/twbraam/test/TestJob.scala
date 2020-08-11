package org.twbraam.test

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

import org.apache.flink.api.common.state.{MapStateDescriptor, ValueStateDescriptor}
import org.apache.flink.api.common.typeinfo.{BasicTypeInfo, TypeHint, TypeInformation}
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.datastream.{BroadcastStream, DataStream}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.twbraam.test.housesource.{House, HouseSource}
import org.twbraam.test.modelsignal.{ModelSignal, ModelSignalSource}

object TestJob {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val houses: DataStream[House] = env
      .addSource(new HouseSource)
      .name("houses")

    val modelSignals: BroadcastStream[ModelSignal] = env
      .addSource(new ModelSignalSource)
      .broadcast(new MapStateDescriptor("RulesBroadcastState",
        BasicTypeInfo.INT_TYPE_INFO,
        TypeInformation.of(new TypeHint[String] {}))
      )

    val connected = houses
      //.windowAll(TumblingEventTimeWindows.of(Time.minutes(5)))
      .connect(modelSignals)
      .process(new HouseBroadcastFunction)
      .print

    env.execute("Evolving Stream Model Test")
  }
}
