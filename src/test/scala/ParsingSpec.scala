import bazaar.data.ParameterizedQuery
import org.scalatest.{FlatSpec, Matchers}

class ParsingSpec extends FlatSpec with Matchers {
  behavior of "ParameterizedQuery should parse parameter names correctly"

  val basicSql = "SELECT id, Name, Age, TS FROM TestTable WHERE Age < :age OR id = :id"

  it should "parse out parameter names" in {
    val q = new ParameterizedQuery(basicSql)
    q.params should contain allOf(":age", ":id")
  }

  it should "transform parameters into '?'" in {
    val q = new ParameterizedQuery(basicSql)
    q.parameterizedSql.split(" ") should contain noneOf (":age", ":id")
    q.parameterizedSql should contain ('?')
  }
}
