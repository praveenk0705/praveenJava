package fi.ohr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection connection=null;
	PreparedStatement stSelect=null;

	@Override
	public void init(ServletConfig config)throws ServletException
	{	
			super.init(config);
			
				ServletContext cxt=getServletContext();
				connection = (Connection) cxt.getAttribute("global");
	}
	@Override
	public void destroy()
	{
		try
		{
			if(stSelect!=null)
				stSelect.close();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ResultSet result=null;
			
			
			try
			{
				
				String userName=request.getParameter("txtName");
				String password=request.getParameter("txtPass");
				System.out.println(userName);
				System.out.println(password);
				
				if(userName.equals("admin")  && password.equals("admin"))
				{
					HttpSession session=request.getSession();
					session.setAttribute("userName", userName);
					response.sendRedirect("adminMgmt.jsp");
				}
				else
				{
					stSelect=connection.prepareStatement("select * from registration where username=? and password=?");
					
					stSelect.setString(1, userName);
					stSelect.setString(2, password);
					result=stSelect.executeQuery();
					PrintWriter out=response.getWriter();

					if(result.next())
					{
						out.println("Login Successfull......");
						HttpSession session=request.getSession();
						session.setAttribute("userName", userName);
						response.sendRedirect("booking.jsp");
					}
					else
						out.println("UserName and Password is invalid");
					/*else
						response.sendRedirect("Home.jsp");*/
			    }
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(result!=null)
						result.close();
					
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	private char[] alert(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	}
