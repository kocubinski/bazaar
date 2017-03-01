import bazaar.data.ConnectionPool
import org.scalatest.{FlatSpec, Matchers}

class ConnectionSpec extends FlatSpec with Matchers with LoanPattern {

  behavior of "ConnectionPool behavior of data layer"

  val cp = ConnectionPool

  it should "borrow a connection" in {
    val conn = cp.borrow
    try {
      conn should not be null
    } finally {
      conn.close
    }
  }

  it should "use loan pattern for borrowing a connection" in {
    using(cp.borrow) { c =>
      c should not be null
    }
  }
}
