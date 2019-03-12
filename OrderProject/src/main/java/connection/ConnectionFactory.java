package connection;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//GHITUN PATRICIA ROXANA - 30227
public class ConnectionFactory 
{
	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://127.0.0.1:3307/bd";
	private static final String USER="root";
	private static final String PASS ="";
	private static ConnectionFactory singleInstance = new ConnectionFactory ();
	private Connection connection;
	
	public ConnectionFactory()
	{
		try
		{
			Class.forName(DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() throws SQLException
	{
        try
        {
           connection = DriverManager.getConnection(DBURL, USER, PASS);
           //System.out.println("Connection success");
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,"An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
	
	public static Connection getConnection() throws SQLException 
	{
		return singleInstance.createConnection();
	}
	
	public static void close(Connection con) 
	{
		if (con != null) 
		{
			try 
			{
				con.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "close - connection error");
			}
		}
	}

	public static void close(Statement statement) 
	{
		if (statement != null) 
		{
			try 
			{
				((Connection) statement).close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "close - statement error");
			}
		}
	}

	public static void close(ResultSet result) 
	{
		if (result != null) 
		{
			try 
			{
				result.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "close - ResultSet error");
			}
		}
	}
		
}
