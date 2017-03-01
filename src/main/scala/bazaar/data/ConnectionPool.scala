package bazaar.data

import java.sql.Connection
import java.util.Properties

import org.postgresql.ds.PGPoolingDataSource

object ConnectionPool {
  private def getSettings : Properties = {
    val props = new Properties
    val in = getClass.getClassLoader.getResourceAsStream("jdbc.properties")
    props.load(in)
    in.close()
    props
  }

  private lazy val dataSource = {
    val source = new PGPoolingDataSource
    source.setDataSourceName("default")
    val p: Properties = getSettings
    source.setServerName(p.getProperty("server"))
    source.setPortNumber(Integer.parseInt(p.getProperty("port")))
    source.setUser(p.getProperty("user"))
    source.setPassword(p.getProperty("password"))
    source.setDatabaseName(p.getProperty("db"))
    source.setMaxConnections(Integer.parseInt(p.getProperty("maxConnections")))
    source
  }

  def borrow : Connection = dataSource.getConnection
}