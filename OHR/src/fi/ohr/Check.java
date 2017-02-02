package fi.ohr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;


public class Check extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	
	    Connection connection=null;
		PreparedStatement stSelect=null;
		PreparedStatement stSelect1=null;
		PreparedStatement stSelect2=null;
		
		

    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ServletContext cxt=getServletContext();
		connection = (Connection) cxt.getAttribute("global");
		
		
	try {
		 int noOfDays=0;
		 int roomAvailable=0;
		 int id=0;
		 float cost=0;
		 float totalCost=0;
		 
		 
		 stSelect2=connection.prepareStatement("select * from Room where roomtype=?");
		  String roomType=request.getParameter("roomSelect");

		 stSelect2.setString(1,roomType);
		 ResultSet result2 = stSelect2.executeQuery();
		 
		 if(result2.next())
		 {
		      id=Integer.parseInt(result2.getString("RoomId"));
			  cost=Float.parseFloat(result2.getString("charges"));
			  roomAvailable=Integer.parseInt(result2.getString("totalRooms"));
				System.out.println(roomAvailable);
		 }
		
		System.out.println(roomType);
		
		
		String totalPerson=request.getParameter("personNo");
		 System.out.println(totalPerson);
	    String totalRoom=request.getParameter("roomNo");
	    System.out.println(totalRoom);
	    
	    int tempTotalRoom=Integer.parseInt(totalRoom);
	    System.out.println(tempTotalRoom);
		
		String InDay	=request.getParameter("daySelectI");
		String InMonth	=request.getParameter("monthSelectI");
		String InYear	=request.getParameter("yearSelectI");
		
		String checkInDate=InDay+"-"+InMonth+"-"+InYear;
		System.out.println(checkInDate);

		
		
		
			PreparedStatement stDate= null;
			stDate = connection.prepareStatement("select to_date(?) - sysdate from dual ");
			stDate.setString(1, checkInDate);
			ResultSet dateRes=stDate.executeQuery();
			if(dateRes.next())
			{
			int re=dateRes.getInt(1);
			System.out.println("prveen"+re);
			if(re<0)
			{
				System.out.println("invalid date pku");
				response.sendRedirect("error1.jsp");
				return;
			}
			}
		

		
		
		
		String OutDay	=request.getParameter("daySelectO");
		String OutMonth	=request.getParameter("monthSelectO");
		String OutYear	=request.getParameter("yearSelectO");

		
		String checkOutDate=OutDay+"-"+OutMonth+"-"+OutYear;
		System.out.println(checkOutDate);
		
		
		
		HttpSession session = request.getSession(false);

		stSelect1=connection.prepareStatement("select to_date(?) - to_date(?) from dual");
		stSelect1.setString(1,checkOutDate);
		stSelect1.setString(2,checkInDate);
		
		ResultSet result1=stSelect1.executeQuery();
		if(result1.next())
		{
			 noOfDays=Integer.parseInt(result1.getString(1));
			 if(noOfDays==0)
				 noOfDays=1;
		}
	
		System.out.println("No OF days "+ noOfDays);
		session.setAttribute("totalDays", noOfDays);
		
		stSelect = connection.prepareStatement("select sum(totalRoom) from room_booking " +
		           "where checkInDate between ? and ? or checkOutDate between ? " +
		                  "and ? group by roomId having roomId= ?");
		
		
		stSelect.setString(1, checkInDate);
		stSelect.setString(2, checkOutDate);
		stSelect.setString(3, checkInDate);
		stSelect.setString(4, checkOutDate);
		stSelect.setInt(5, id);
		
		System.out.println("before executing No records in the Database");
		ResultSet result=stSelect.executeQuery();
		System.out.println("No records in the Database");
			
		int totalNoOfRooms=0;
		System.out.println(totalNoOfRooms);
		
		
		System.out.println(totalNoOfRooms);
		System.out.println("hiiibyeeeeeeeeeee");
		
		System.out.println(roomAvailable);
		
		System.out.println("bye");
		
	    int roomLeft = roomAvailable-totalNoOfRooms;
	    System.out.println("roomLeft");
	   
		
		if(result.next())
		{
			totalNoOfRooms=Integer.parseInt(result.getString(1));
			System.out.println(result.getString(1));
			 System.out.println("The total no of rooms booked is"+totalNoOfRooms);
		    System.out.println(roomLeft);
		
		    if (totalNoOfRooms<roomAvailable)
		  {
		    	 totalCost=(tempTotalRoom*cost*noOfDays);
		    	 System.out.println("No of daysssssss"+noOfDays);
		    	 System.out.println(totalCost);
		    	
		    	session.setAttribute("roomId", id);
		    	session.setAttribute("roomType",roomType);
		    	session.setAttribute("cost",totalCost);
		    	session.setAttribute("checkIn",checkInDate);
				session.setAttribute("checkOut",checkOutDate);
				session.setAttribute("roomId",id);
				session.setAttribute("person",totalPerson);
				session.setAttribute("noRoom",totalRoom);
		    	
				System.out.println("The seesion at set value is "+session.getAttribute("person"));
				
		    	//System.out.println(name);
			    System.out.println("Rooms are available");
			    
			    if(session.getAttribute("userName")!=null)
			    {
			    	System.out.println("inside if");
			     response.sendRedirect("chooseServices.jsp");
			    }
			     else
			     {
			    	 System.out.println("else");
			    	response.sendRedirect("showingAvailability.jsp");
			     }
			    	//response.sendRedirect("FinalBooking.jsp");
			  
		  }
		    else
		    {
		    	 System.out.println("Rooms are not available");
		         response.sendRedirect("booking.jsp");
		    }    
		    
		}
		else
		{
			System.out.println("Not bbbbb fired");
	    	 totalCost=(tempTotalRoom*cost*noOfDays);	
	    	 System.out.println("No of daysssssss,,else loop"+noOfDays);
			
	    	 System.out.println(totalCost);
	    	
	    	session.setAttribute("roomId", id);
	    	session.setAttribute("roomType",roomType);
	    	session.setAttribute("cost",totalCost);
	    	session.setAttribute("checkIn",checkInDate);
			session.setAttribute("checkOut",checkOutDate);
			session.setAttribute("roomId",id);
			session.setAttribute("person",totalPerson);
			session.setAttribute("noRoom",totalRoom);
			System.out.println(cost);
			System.out.println(tempTotalRoom);
			System.out.println(totalCost);
			System.out.println("The seesion at set value is in Else waala "+session.getAttribute("cost"));
	    	
			if(session.getAttribute("userName")!=null)    
			     response.sendRedirect("chooseServices.jsp");
			    
			    else
			    	response.sendRedirect("showingAvailability.jsp");			
		    //response.sendRedirect("FinalBooking.jsp");
		}
	} 
	
	catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  } 

}
