package cy.ac.cy.epl441.mysql_dsf;

import java.sql.Driver;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jdbc.DataSourceFactory;

import com.mysql.cj.jdbc.MysqlDataSource;

@Component(
		property = {
				DataSourceFactory.OSGI_JDBC_DRIVER_CLASS+"="+"com.mysql.cj.jdbc.Driver",
				DataSourceFactory.OSGI_JDBC_DRIVER_VERSION+"="+"8.0.16",
				DataSourceFactory.OSGI_JDBC_DRIVER_NAME+"="+"com.mysql.cj.jdbc.Driver"
		}
)
public class MySqlDataSourceFactory implements DataSourceFactory {

	@Override
	public DataSource createDataSource(Properties props) throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL(props.getProperty(DataSourceFactory.JDBC_URL));
		ds.setUser(props.getProperty(DataSourceFactory.JDBC_USER));
		ds.setPassword(props.getProperty(DataSourceFactory.JDBC_PASSWORD));
		return ds;
	}

	@Override
	public ConnectionPoolDataSource createConnectionPoolDataSource(Properties props) throws SQLException {
		// Unnecessary.
		throw new IllegalStateException("Not implemented yet");
	}

	@Override
	public XADataSource createXADataSource(Properties props) throws SQLException {
		// Unnecessary.
		throw new IllegalStateException("Not implemented yet");
	}

	@Override
	public Driver createDriver(Properties props) throws SQLException {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Not implemented yet");
	}

}
