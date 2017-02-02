package fi.ohr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class roomModify extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       

	 Connection connection=null;
     PreparedStatement stSelect=null;
     
     int roomId=0;
     String roomType=null;
     int roomCharges =0;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	                     throws ServletException, IOException 
    {		
		ServletContext cxt=getServletContext();
		connection = (Connection) cxt.getAttribute("global");
		
		String str1=request.getParameter("btnAdd");
		String str2=request.getParameter("btnDelete");
		String str3=request.getParameter("btnUpdate");	
		
		System.out.println(str2);
			
		if(str1!=null)
		{
			if(str1.equals("Add"))
			{
				
				 roomId = Integer.parseInt(request.getParameter("roomId"));
				 roomType = request.getParameter("roomType");
				 roomCharges = Integer.parseInt(request.getParameter("roomCharges"));
				
				System.out.println(str1);
					try 
					{
						stSelect=connection.prepareStatement("insert into room values(?,?,?)");
						stSelect.setInt(1, roomId);
						stSelect.setString(2, roomType);
						stSelect.setInt(3, roomCharges);					
						stSelect.executeUpdate();
						response.sendRedirect("roomModify.jsp");
					}
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		else
		{
			if(str3!=null)
			{
			   if(str3.equals("Update"))
			  {
				   roomId = Integer.parseInt(request.getParameter("roomId"));
				   roomType = request.getParameter("roomType");
				   roomCharges = Integer.parseInt(request.getParameter("roomCharges"));
				   
				   System.out.println(str3);
				   
				try 
				{
					stSelect=connection.prepareStatement("update room set roomType=? ,Charges=? where roomId=?");
					   
					stSelect.setString(1,roomType);
					stSelect.setInt(2,roomCharges);
					stSelect.setInt(3,roomId);
					
					System.out.println(roomType);
					System.out.println(roomCharges);
					System.out.println(roomId);
					
					stSelect.executeUpdate();
					response.sendRedirect("roomModify.jsp");
					
					System.out.println("Done updation");
				} 
				
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		else 
		{
			if(str2.equals("Delete"))			   
			{
				roomId = Integer.parseInt(request.getParameter("roomId"));
				
				try 
				{
					stSelect=connection.prepareStatement("delete from room where roomId=?");
					stSelect.setInt(1,roomId);
					stSelect.executeUpdate();
					response.sendRedirect("roomModify.jsp");
					System.out.println("Deletion Done");
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 }//Else of last
			
		}
   }
}
