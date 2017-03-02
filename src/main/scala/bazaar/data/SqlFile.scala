package bazaar.data

import scala.collection.mutable.Map
import scala.io.Source

class SqlFile(val queries: Map[String, String]) {
  def exec(name: String) = {
  }
}

object SqlFile {
  def apply(file: String) : SqlFile = {
    new SqlFile(scan(file))
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
