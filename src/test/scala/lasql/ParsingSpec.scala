import bazaar.data.ParameterizedQuery
import org.scalatest.{FlatSpec, Matchers}

class ParsingSpec extends FlatSpec with Matchers {
  behavior of "ParameterizedQuery should parse parameter names correctly"

  it should "parse out parameter names" in {
    val sql = "SELECT id, Name, Age, TS FROM TestTable WHERE Age < :age OR id = :id"
    val q = new ParameterizedQuery(sql)
    q.params should contain allOf(":age", ":id")
  }
}
