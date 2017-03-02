package bazaar.data

import java.sql.{Date, PreparedStatement, Time, Timestamp}

object TypeMapper {
  def set(stmt: PreparedStatement, index: Int, value: Any): Unit = {
    value match {
      case x: Int => stmt.setInt(index, x)
      case x: Long => stmt.setLong(index, x)
      case x: Double => stmt.setDouble(index, x)
      case x: Short => stmt.setShort(index, x)
      case x: Float => stmt.setFloat(index, x)
      case x: Timestamp => stmt.setTimestamp(index, x)
      case x: Date => stmt.setDate(index, x)
      case x: Time => stmt.setTime(index, x)
      case x: String => stmt.setString(index, x)
      case _ => throw new UnsupportedOperationException(s"Unsupported type: ${value.getClass.getName}")
    }
  }
}
