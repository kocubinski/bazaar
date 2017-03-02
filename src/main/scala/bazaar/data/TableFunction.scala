package bazaar.data

import java.sql.Connection

class TableFunction(name : String)(implicit conn : Connection = ConnectionPool.borrow) {

}
