// scalastyle:off println
package org.apache.spark.examples.ml

// $example on$
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
// $example off$
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

/**
  * An example for computing correlation matrix.
  * Run with
  * {{{
  * bin/run-example ml.CorrelationExample
  * }}}
  */
object CorrelationExample {

  def main(args: Array[String]): Unit = {

    //off logger
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)


    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      .getOrCreate()
    import spark.implicits._

    // $example on$
    val data = Seq(

      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0)

    )

    val df = data.map(Tuple1.apply).toDF("features")
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1")

    val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")
    // $example off$

    spark.stop()
  }
}
// scalastyle:on println