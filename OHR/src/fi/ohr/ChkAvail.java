package fi.ohr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChkAvail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Connection connection=null;
	PreparedStatement stSelect=null;

	@Override
	public void init(ServletConfig config)throws ServletException
	{
	
		super.init(config);
		try {
			ServletContext cxt=getServletContext();
			connection = (Connection) cxt.getAttribute("global");
			/*stSelect = connection.prepareStatement("select sum(totalRoom) from room_booking " +
					           "where checkInDate between ? and ? or checkOutDate between ? " +
					                  "and ? group by roomId having roomId= ?");*/
			
			stSelect=connection.prepareStatement("select * from room_booking where checkInDate=?");
			
			/*stSelect = connection.prepareStatement("select sum(totalRoom) from room_booking " +
			           "where checkInDate between '20-jul-11' and '27-jul-11' or checkOutDate between '20-jul-11' and '27-jul-11' group by roomId having roomId= 1");*/
			
			/*ResultSet result =stSelect.executeQuery();	
			//int i=Integer.parseInt(result.toString());
			
		
			if(result.next())
			{
				int noOfRooms =Integer.parseInt(result.getString(1));
				
				if(noOfRooms>=12)
					System.out.println("No Rooms Available");
				else
					System.out.println("Rooms Available");
				//String str=result.getString("username");
				//System.out.println(result.getString(1));
				//System.out.println(noOfRooms);
				System.out.println("Done");
			}*/
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		int roomId=0;
	
		
		try {
			String chkIn=request.getParameter("firstinput");
			//Date chkIn=tempChkIn;
			String chkOut=request.getParameter("secondinput");
			
			
			
				DateFormat formatter ; 
				 Date date ; 
				  formatter = new SimpleDateFormat("yy/MM/dd");
				  date = (Date)formatter.parse(chkIn);
				 
				  System.out.println(date);

			
			String roomType=request.getParameter("roomSelect");
			if(roomType.equals("Business Suite"))
			{
				roomId=1;
			}
			else if(roomType.equals("Royal Suite"))
			{
				roomId=2;
			}
			else if(roomType.equals("Economic Suite"))
			{
				roomId=3;
			}
			int totalRoom=Integer.parseInt(request.getParameter("roomNo"));
			
			
				
					
					stSelect.setDate(1, date);
					
					/*stSelect.setString(2, chkOut);
					stSelect.setString(3, chkIn);
					stSelect.setString(4, chkOut);
					stSelect.setInt  (5, roomId);*/
					
					ResultSet result =stSelect.executeQuery();
					
					int noOfRooms =Integer.parseInt(result.getString(1));
				    System.out.println(noOfRooms);
				    
					
					/*if(noOfRooms>=12)
						System.out.println("No Rooms Available");
					else
						System.out.println("Rooms Available");
					System.out.println("Done");*/
				    
					if(result.next())
					{
					   noOfRooms =Integer.parseInt(result.getString(1));
					    System.out.println(noOfRooms);
						
						if(noOfRooms>=12)
							System.out.println("No Rooms Available");
						else
							System.out.println("Rooms Available");
						System.out.println("Done");
					}
				
			
			
			System.out.println( chkIn);
			System.out.println( chkOut);
			System.out.println( roomType);
			System.out.println( totalRoom);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
