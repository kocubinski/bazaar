package LaSql

import scala.collection.mutable.Map
import scala.io.Source

class LaSql(val queries: Map[String, String]) { 
  def exec(name: String) = {
  }
}

object LaSql {
  def apply(file: String) : LaSql = {
    new LaSql(scan(file))
  }

  def scan(file: String) : Map[String, String] = {
    val regex = """^\s*--\sname:\s*(\S+)""".r
    val source = Source.fromResource(file)
    val queries = Map[String, String]()
    var queryName = ""

    for (line <- source.getLines) {
      regex.findFirstMatchIn(line) match {
        case Some(name) => queryName = name.group(1) 
        case None => queries.put(queryName, queries.getOrElse(queryName, "") + line + "\n")
      }
    }
    
    queries
  }
}
