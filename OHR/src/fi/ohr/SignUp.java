package fi.ohr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp extends HttpServlet
{
	private static final long serialVersionUID = 1L;       
 
	Connection connection=null;
	PreparedStatement stInsert=null;

public SignUp()
{
    super();
    // TODO Auto-generated constructor stub
}


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	// TODO Auto-generated method stub
	
	ServletContext cxt=getServletContext();
	connection = (Connection) cxt.getAttribute("global");
	if(connection==null)
		System.out.println("connection not present.....");

	
		try {
			
			
				String firstName = request.getParameter("fname");
				String lastName = request.getParameter("lname");
				String userName = request.getParameter("uname");
				String password = request.getParameter("password");
				String conPassword = request.getParameter("cpassword");
				String add = request.getParameter("address");
				String gender = request.getParameter("gender");
				String sQues = request.getParameter("sQues");
				String sAns = request.getParameter("sAns");
				
				
				System.out.println(firstName);
				System.out.println(lastName);
				System.out.println(userName);
				System.out.println(password);
				System.out.println(conPassword);
				System.out.println(add);
				System.out.println(gender);			
				
				String date = request.getParameter("DOB");
				String month = request.getParameter("monthSelectI");
				String year = request.getParameter("yearSelectI");
				
				String dob = date+"-"+month+"-"+year;
				
				String EmailId = request.getParameter("email");
				String contact = request.getParameter("contact");
				
				System.out.println(firstName);
				System.out.println(date);
				System.out.println(month);
				System.out.println(year);
				System.out.println(dob);
				
				stInsert=connection.prepareStatement("insert into registration values(?,?,?,?,?,?,?,?,?,?,?,?)");
				
				
				stInsert.setString(1, firstName);
				stInsert.setString(2, lastName);
				stInsert.setString(3, userName);
				stInsert.setString(4, password);
				stInsert.setString(5, conPassword);
				stInsert.setString(6, add);
				stInsert.setString(7, gender);
				stInsert.setString(8, dob);
				stInsert.setString(9, EmailId);
				stInsert.setString(10, contact);
				stInsert.setString(11, sQues);
				stInsert.setString(12, sAns);
				
				
			
				int result = stInsert.executeUpdate();
				PrintWriter out=response.getWriter();
				response.sendRedirect("Home.jsp");
				
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

}
