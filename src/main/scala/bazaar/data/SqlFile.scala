package bazaar.data

import scala.collection.mutable
import scala.io.Source

class SqlFile(val queries: scala.collection.Map[String, String]) {
  def getQuery(name: String) : ParameterizedQuery = {
    queries.get(name) match {
      case Some(sql) => new ParameterizedQuery(sql)
      case None => throw new IllegalArgumentException(s"no query found with name $name")
    }
  }
}

/*
 * TODO caching with TrieMap
 * 1) hash file
 * 2) If same, return cache. if different, invalidate cache.
 */
object SqlFile {
  def apply(file: String) : SqlFile = {
    new SqlFile(scan(file))
  }

  def scan(file: String) : mutable.Map[String, String] = {
    val regex = """^\s*--\sname:\s*(\S+)""".r
    val source = Source.fromResource(file)
    val queries = mutable.Map[String, String]()
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
