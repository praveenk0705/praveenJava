package fi.ohr;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class Globalconn  implements ServletContextListener  {
	Connection connection;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		System.out.println("Context Destroyed");
		try {
			if(connection!=null)
				connection.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		try {
			System.out.println("Context Initialise, Aloocating Resource");
			ServletContext context = arg0.getServletContext();
			String driver = context.getInitParameter("driverClass");
			String dbUrl = context.getInitParameter("dbUrl");
			String userName = context.getInitParameter("userName");
			String password = context.getInitParameter("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(dbUrl,userName,password);
			context.setAttribute("global",connection);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	

	

	

}
