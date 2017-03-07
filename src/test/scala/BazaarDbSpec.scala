import bazaar.core.LoanPattern
import bazaar.data.SqlFile
import org.scalatest.{FlatSpec, Matchers}

/*
 * These tests will only run / succeed against the spec published in schema.sql
 * They should be broken into an app specific project when I do the sbt refactoring.
 */
class BazaarDbSpec extends FlatSpec with Matchers with LoanPattern {
  behavior of "Queries against bazaar db work properly"

  val seed = SqlFile("sql/seed.sql")

  it should "delete and insert rows into a table" in {
    val race = "abc"

    using (seed.getQuery("delete_race").set("id", race))(st => st.delete())

    using(seed.getQuery("insert_race").set("id", race))(st => {
      val res = st.insert()
      res should equal(1)
    })

    using (seed.getQuery("delete_race").set("id", race))(st => st.delete())
  }

}
