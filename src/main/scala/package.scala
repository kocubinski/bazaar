import LoanPattern.Closable

package object bazaar {
  def using[R <: Closable, A](resource: R)(f: R => A): A = LoanPattern.using(resource)(f)
}