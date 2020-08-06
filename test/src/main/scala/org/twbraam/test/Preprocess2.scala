package org.twbraam.test

import scala.util.{Success, Try}

object Preprocess2 {
  // Directly to float
  val toZero: String => Float =
    value => Try(value.toFloat) match {
      case Success(v) => v
      case _ => 0f
    }

  val toBool: String => Float =
    value => Try(value.toInt) match {
      case Success(v) if v < 4000 => 1f
      case _ => 0f
    }

  val toCode: String => Float =
    value => Map("Gd" -> 4f, "TA" -> 3f, "Ex" -> 5f, "Fa" -> 2f, "Po" -> 1f).getOrElse(value, 0f)

  def toMean(mean: Float): String => Float =
    value => Try(value.toFloat) match {
      case Success(v) => v
      case _ => mean
    }

  // OneHotCoded
  val toNone: String => String =
    value => if (value.isEmpty) "None" else value

  def toMode(mode: String): String => String =
    value => if (value.isEmpty) mode else value



}
