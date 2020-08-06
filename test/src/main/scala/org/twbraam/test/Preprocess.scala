package org.twbraam.test

import scala.collection.mutable.ArrayBuffer
import scala.util.{Success, Try}

object Preprocess {
  val toNone: List[Int] => Array[String] => Array[String] =
    to(value => if (value.isEmpty) "None" else value)

  val toZero: List[Int] => Array[String] => Array[String] =
    to(value => if (value.isEmpty) "0" else value)

  val toCode: List[Int] => Array[String] => Array[String] =
    to(value => Map("Gd" -> "4", "TA" -> "3", "Ex" -> "5", "Fa" -> "2", "Po" -> "1").getOrElse(value, "0"))

  val toBool: List[Int] => Array[String] => Array[String] =
    to(value => Try(value.toInt) match {
      case Success(v) if v < 4000 => "1"
      case _ => "0"
    })

  def toMode(mode: String): List[Int] => Array[String] => Array[String] =
    to(value => if (value.isEmpty) mode else value)

  def toMean(mean: String): List[Int] => Array[String] => Array[String] =
    to(value => if (value.isEmpty) mean else value)

  def to(f: String => String): List[Int] => Array[String] => Array[String] =
    idx => input => idx.foldLeft(input)((acc, i) => acc.updated(i, f(acc(i))))
}
