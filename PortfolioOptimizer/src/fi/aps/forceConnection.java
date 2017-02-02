package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class forceConnection
 */
@WebServlet("/forceConnection")
public class forceConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forceConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Connection connection = null;
		ResultSet result = null;
		PrintWriter out = response.getWriter();
		try 
		{
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@prophet.njit.edu:1521:course";
			String user = "pku5";
			String password = "KgptDqe0D" ;
			System.out.println(driver+dbUrl+user+password);
			
			Class.forName(driver);
			connection = DriverManager.getConnection(dbUrl, user, password);
			if(connection!= null){
				System.out.println("made connection through doget");
				out.println("made connection through doget");
				connection.setAutoCommit(false);
			}
			if(connection==null)
			{
				System.out.println("Connection Failed");
				return;
			}
			ServletContext context = getServletContext();
			context.setAttribute("globalConnection", connection);
		} 
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
