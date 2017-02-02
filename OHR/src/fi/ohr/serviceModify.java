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


public class serviceModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	 Connection connection=null;
     PreparedStatement stSelect=null;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	                     throws ServletException, IOException 
    {
		
		ServletContext cxt=getServletContext();
		connection = (Connection) cxt.getAttribute("global");
		
		String str1=request.getParameter("btnAdd");
		String str2=request.getParameter("btnDelete");
		String str3=request.getParameter("btnUpdate");
		
		
		System.out.println(str2);
		int service_Id =0;
		String service_Name=null;
		int price;
		
		
	if(str1!=null)
	{
		if(str1.equals("Add"))
		{
			
			service_Id= Integer.parseInt(request.getParameter("service_Id"));
		    service_Name = request.getParameter("service_Name");
			price = Integer.parseInt(request.getParameter("price"));
			
			
			System.out.println(str1);
				try {
					stSelect=connection.prepareStatement("insert into services values(?,?,?)");

					stSelect.setString(1, service_Name);
					stSelect.setInt(2, service_Id);
					stSelect.setInt(3, price);
					
					stSelect.executeUpdate();
					response.sendRedirect("serviceModify.jsp");
				} catch (SQLException e) {
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
			   
			   service_Id= Integer.parseInt(request.getParameter("service_Id"));
			    service_Name = request.getParameter("service_Name");
				price = Integer.parseInt(request.getParameter("price"));
			   
			System.out.println(str3);
			try 
			{
				stSelect=connection.prepareStatement("update services set service_Name=? ,Price=? where service_Id=?");
				   
				stSelect.setString(1,service_Name);
				stSelect.setInt(2,price);
				stSelect.setInt(3,service_Id);
				
				System.out.println(service_Name);
				System.out.println(service_Id);
				System.out.println(price);
				
				stSelect.executeUpdate();
				response.sendRedirect("serviceModify.jsp");
				
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
				   service_Id= Integer.parseInt(request.getParameter("service_Id"));
				  
			try {
				stSelect=connection.prepareStatement("delete from services where service_Id=?");
				stSelect.setInt(1,service_Id);
				stSelect.executeUpdate();
				response.sendRedirect("serviceModify.jsp");
				System.out.println("Deletion Done");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 }//Else of last
		
	}
	}

	
	

}

