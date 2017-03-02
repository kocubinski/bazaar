package bazaar.data

import java.sql.{Connection, PreparedStatement}

class ParameterizedQuery(sql: String)(implicit conn : Connection = ConnectionPool.borrow) {
  type collector = (Seq[String], String)

  lazy val params: Seq[String] = {
    val p = ParameterizedQuery.Prefix
    val res = sql.foldLeft((Seq[String](), ""))((t: collector, c: Char) =>
      c match {
        case `p` => (t._1, c.toString)
        case ' ' => if (t._2 isEmpty) t else (t._1 :+ t._2, "")
        case _ => if (t._2 isEmpty) t else (t._1, t._2 + c)
      })
    if (res._2 isEmpty) res._1 else res._1 :+ res._2
  }

  lazy val parameterizedSql: String = params.foldLeft(sql)(_.replace(_, "?"))

  lazy val statement: PreparedStatement = conn.prepareStatement(parameterizedSql)

  def set(param :String, value: Any) : this.type = {
    val p = s"ParameterizedQuery.Prefix$param"
    if (!params.contains(p))
      throw new IllegalArgumentException(s"$p is not present in the SQL statement.")

    val i = params.indexOf(p)
    TypeMapper.set(statement, i, value)
    this
  }

  def close() : Unit = {
    // Questionable, this is (possibly) injected
    conn.close()
    statement.close()
  }
}

object ParameterizedQuery {
  val Prefix: Char = ':'
}
