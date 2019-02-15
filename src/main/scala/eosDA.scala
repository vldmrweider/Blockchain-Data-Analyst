import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

case class Account(acc_name:String, rate:String)

object eosDA {
  def main(args: Array[String]) {

    //off logger
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

   // val logFile = "/usr/lib/spark/README.md"
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      .getOrCreate()

    import spark.implicits._



    val df = spark.read.json("/home/vldmr/work/m2h/14022019.json").as[Account]

    df.show(20)


    //df.createOrReplaceTempView("account")
    //spark.sql("select * from account").show(20)

//    val logData = spark.read.textFile(logFile).cache()
//
//    val numAs = logData.filter(line => line.contains("a")).count()
//    val numBs = logData.filter(line => line.contains("b")).count()
//    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}