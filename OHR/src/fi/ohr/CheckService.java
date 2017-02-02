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
import javax.servlet.http.HttpSession;




public class CheckService extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	 Connection connection=null;
		PreparedStatement stSelect=null;
		
   
    public CheckService() 
    {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	                              throws ServletException, IOException 
	{
	
		try {
			
			float servicePriceL=0; 
			float servicePriceC=0;
			float servicePriceI=0;	
			float servicePriceG=0;
			float servicePriceCity=0;
			float totalServiceCharge=0;
			
			
			
			
			ServletContext cxt=getServletContext();
			connection = (Connection) cxt.getAttribute("global");
			
			HttpSession session = request.getSession(false);
			int totalDays=(Integer)session.getAttribute("totalDays");
			
			System.out.println(totalDays);
			
			stSelect = connection.prepareStatement("select price from services where service_name=?");
			
			
			String str1=request.getParameter("chkgrad1");
			String str2=request.getParameter("chkgrad2");
			String str3=request.getParameter("chkgrad3");
			String str4=request.getParameter("chkgrad4");
			String str5=request.getParameter("chkgrad5");		
			
			if(str1 != null)
			{
				System.out.println("1St if");
				stSelect.setString(1,str1);
				ResultSet result1 = stSelect.executeQuery();
				if(result1.next())
				{
					servicePriceL = Float.parseFloat(result1.getString(1));
					totalServiceCharge=totalServiceCharge+servicePriceL;
					System.out.println("The total charge is1 "+totalServiceCharge);
					System.out.println(servicePriceL);
					session.setAttribute("str1", str1);
					
				}					
			
			}
			
			if(str2 != null)
			{
				stSelect.setString(1,str2);
				ResultSet result2 = stSelect.executeQuery();
				if(result2.next())
				{
					servicePriceC = Float.parseFloat(result2.getString(1));	
					
					totalServiceCharge=totalServiceCharge+servicePriceC;
					System.out.println("The total charge is2 "+totalServiceCharge);
					System.out.println(servicePriceC);
					session.setAttribute("str2", str2);
					
				}	
						
			}
			if(str3 != null)
			{
				stSelect.setString(1,str3);
				ResultSet result3 = stSelect.executeQuery();
				if(result3.next())
				{
					servicePriceI = Float.parseFloat(result3.getString(1));	
					totalServiceCharge=totalServiceCharge+servicePriceI;
					System.out.println("The total charge is 3 "+totalServiceCharge);
					System.out.println(servicePriceI);
					session.setAttribute("str3", str3);
					
				}	
							
			}
			if(str4 != null)
			{
				stSelect.setString(1,str4);
				ResultSet result4 = stSelect.executeQuery();
				if(result4.next())
				{
					servicePriceG = Float.parseFloat(result4.getString(1));	
					totalServiceCharge=totalServiceCharge+servicePriceG;
					System.out.println("The total charge is 4"+totalServiceCharge);
					System.out.println(servicePriceG);
					session.setAttribute("str4", str4);
					
				}	
							
			}
			if(str5 != null)
			{
				stSelect.setString(1,str5);
				ResultSet result5 = stSelect.executeQuery();
				if(result5.next())
				{
					servicePriceCity = Float.parseFloat(result5.getString(1));	
					totalServiceCharge=totalServiceCharge+servicePriceCity;
					System.out.println(servicePriceCity);
					System.out.println("The total charge is 5"+totalServiceCharge);
					session.setAttribute("str5", str5);
					
				}	
						
			}
			
			totalServiceCharge=totalServiceCharge*totalDays;
			
			System.out.println(str1);
			System.out.println(str2);
			System.out.println(str3);
			System.out.println(str4);
			System.out.println(str5);
			
			session.setAttribute("LaundaryN",str1);
			session.setAttribute("CabN",str2);
			session.setAttribute("InternetN",str3);
			session.setAttribute("GymN",str4);
			session.setAttribute("CityN",str5);
			
			session.setAttribute("LaundaryP",servicePriceL);
			session.setAttribute("CabP",servicePriceC);
			session.setAttribute("InternetP",servicePriceI);
			session.setAttribute("GymP",servicePriceG);
			session.setAttribute("CityP",servicePriceCity);
			
			session.setAttribute("totalService", totalServiceCharge);
			
			System.out.println(session.getAttribute("LaundaryN"));
			response.sendRedirect("FinalBooking.jsp");
			
		} 
		catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	                                 throws ServletException, IOException 
	{
		
	}

}
