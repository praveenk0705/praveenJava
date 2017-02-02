package fi.ohr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelBooking extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	                       throws ServletException, IOException 
	{
		Connection connection=null;
		PreparedStatement stDelete=null;
		
			//String name=request.getParameter("txtName");
			String checkInDate=request.getParameter("txtCheckIn");
			String checkOutDate=request.getParameter("txtCheckOut");
			HttpSession session=request.getSession(false);
			String name=(String)session.getAttribute("userName");
			
			ServletContext cxt=getServletContext();
			connection = (Connection) cxt.getAttribute("global");
			
		try 
		{
			
			//insert into payment values(?,?,?)
			stDelete=connection.prepareStatement("delete from room_booking where username=? and checkInDate=(to_date(?)) and checkOutDate =(to_date(?))");
			
			
			
			stDelete.setString(1, name);
			stDelete.setString(2, checkInDate);
			stDelete.setString(3, checkOutDate);
			
			
			
			stDelete.executeUpdate();
			
			
			System.out.println("Deletion done");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	

}
