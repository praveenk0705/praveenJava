package fi.ohr;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.rmi.CardValidator;


public class Cancellation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Cancellation() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		
		
		
		
		
		
		
		
		Connection connection=null;
		PreparedStatement stSelect=null;
		PreparedStatement stDelete=null;
		PreparedStatement stUpdateRoom=null;
		PreparedStatement stGetRoom=null;
		PreparedStatement stGetRoomId = null;
		PreparedStatement stGetRoom1 = null;
		
		
		
		
		long cardNo=0;
		int bookedNoOfRoom = 0;
		int bookedRoomId = 0;
		int noOfRoomLeft=0;
		int toAddRoom=0;
		
		ServletContext cxt=getServletContext();
		connection = (Connection) cxt.getAttribute("global");
		
		
		String TempbookingId = request.getParameter("bookingId");
		int bookingId=Integer.parseInt(TempbookingId);
		System.out.println(bookingId);
		String refundAmt = request.getParameter("refundAmt");
		float FinalrefundAmt=Float.parseFloat(refundAmt);
		System.out.println(refundAmt);
		
		
		
		
		
		
		 try {
			stSelect=connection.prepareStatement("select cardno from room_booking where bookingid=?");
			 stSelect.setInt(1, bookingId);
			 ResultSet result=stSelect.executeQuery();
			 if(result.next())
			 {
				 cardNo=result.getInt(1);
				 System.out.println(cardNo);
			 }
			 
			
			 Registry registry = LocateRegistry.getRegistry();
					CardValidator remote =(CardValidator)registry.lookup("CardService");
					//CardValidator remote =(CardValidator)Naming.lookup("CardService");
					
					
					
					boolean flag = remote.CancelBooking(cardNo,FinalrefundAmt);
					//boolean flag = remote.validateCard(666,666,111);
					//boolean flag = remote.CancelBooking(666,1);
					System.out.println("kkk");
					if(flag)
					{
						
						
						
						//to get roomid
						try {
							

							
							stGetRoom = connection.prepareStatement("select * from room_booking where bookingid=?");
							
							//to get the the no of room booked
							stGetRoom.setInt(1, bookingId);
							
							
							ResultSet res1= stGetRoom.executeQuery();
							if(res1.next())
							{
								bookedRoomId = res1.getInt(2);
								System.out.println(" booked roomid"+ bookedRoomId);
								
								bookedNoOfRoom = res1.getInt(5);
								System.out.println(" total  Room the user has booked"+ bookedNoOfRoom);
							}
							
							
							
							stGetRoom1 = connection.prepareStatement("select totalrooms from room where roomid=?");
							
							stGetRoom1.setInt(1,bookedRoomId);
							ResultSet res2= stGetRoom1.executeQuery();
							
							if(res2.next())
							{
							noOfRoomLeft=res2.getInt(1);
							System.out.println(" total . no of room in room table"+noOfRoomLeft);
		
							}
							
							
							//to get the no of room in the room table
							
						//to update	
							
							stUpdateRoom = connection.prepareStatement("update room set totalrooms=? where roomid = ?");
							
							toAddRoom= noOfRoomLeft+bookedNoOfRoom;
							
							System.out.println(" total  no of to add"+toAddRoom);
							
							stUpdateRoom.setInt(1, toAddRoom);
							stUpdateRoom.setInt(2,bookedRoomId);
							stUpdateRoom.executeUpdate();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						
						
						
						
						
						System.out.println("Cancellation Done");
						try {
							stDelete=connection.prepareStatement("delete from room_booking where bookingid=?");
							stDelete.setInt(1, bookingId);
							stDelete.executeUpdate();
							System.out.println("also deleted");
							
							response.sendRedirect("finalCancel.jsp");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
					else
						System.out.println("Cancellation failed");
					
					
					
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		
		/*try {
			stDelete=connection.prepareStatement("delete from room_booking where bookingid=?");
			stDelete.setInt(1, bookingId);
			stDelete.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		
		
		
		
	}

}
