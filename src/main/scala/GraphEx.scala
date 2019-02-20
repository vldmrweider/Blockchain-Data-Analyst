import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.sql.SparkSession
// To make some of the examples work we will also need RDD
import org.apache.spark.rdd.RDD

object GraphEx {

  def main(args: Array[String]): Unit = {

    //off logger
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sc = SparkSession
      .builder
      .appName("GraphX Application")
      .config("spark.master", "local")
      .getOrCreate().sparkContext



    // Create an RDD for the vertices
    val users: RDD[(VertexId, (String, String))] =
      sc.parallelize(
        Array((3L, ("vasya", "student")),
              (7L, ("petya", "postdoc")),
              (5L, ("vlad", "prof")),
              (2L, ("igor", "prof"))
        )
      )



    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
      sc.parallelize(
        Array(Edge(3L, 7L, "like100"),
              Edge(5L, 3L, "like50"),
              Edge(2L, 5L, "like45"),
              Edge(5L, 7L, "like30")))



    // Define a default user in case there are relationship with missing user
    val defaultUser = ("tupator", "Missing")
    // Build the initial Graph
    val graph = Graph(users, relationships, defaultUser)







  }
  }