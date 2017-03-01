import scala.util.control.Exception.ignoring

trait LoanPattern {
  type Closable = { def close(): Unit }

  // can this be java.sql.AutoClosable ?

  def using[R <: Closable, A](resource: R)(f: R => A): A = {
    try {
      f(resource)
    } finally {
      ignoring(classOf[Throwable]) apply {
        resource.close()
      }
    }
  }
}

object LoanPattern extends LoanPattern
