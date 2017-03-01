package bazaar.core

import scala.util.control.Exception.ignoring

trait LoanPattern {
  type Closable = { def close(): Unit }

  // this is very cool..
  // scalac seems to resolve Closable to AutoClosable (parent of java.sql.Connection) based on the shape of type

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
