import bazaar.core.LoanPattern
import bazaar.core.LoanPattern.Closable

package object bazaar {
  def using[R <: Closable, A](resource: R)(f: R => A): A = LoanPattern.using(resource)(f)
}