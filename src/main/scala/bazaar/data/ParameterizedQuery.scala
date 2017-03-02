package bazaar.data

import java.sql.Connection

class ParameterizedQuery(sql: String)(implicit conn : Connection = ConnectionPool.borrow) {
  type collector = (Seq[String], String)

  private var parameterizedSql = sql

  val params: Seq[String] = {
    val res = sql.foldLeft((Seq[String](), ""))((t: collector, c: Char) =>
      c match {
        case ':' => (t._1, ":")
        case ' ' => if (t._2 isEmpty) t else (t._1 :+ t._2, "")
        case _ => if (t._2 isEmpty) t else (t._1, t._2 + c)
      })
    if (res._2 isEmpty) res._1 else res._1 :+ res._2
  }

  def set(param :String, value: Object) : this.type = {
    val i = param.indexOf(param)

  }
}
