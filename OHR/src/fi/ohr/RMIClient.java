package fi.ohr;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fi.rmi.CardValidator;


public class RMIClient extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	 Connection connection=null;
	PreparedStatement stSelect=null;
	PreparedStatement stSelect1=null;
	PreparedStatement stSelect2=null;
	PreparedStatement stSelect3=null;
	
	PreparedStatement txInsert = null;//to insert data into table transaction(basically to get the card no)
	
		
    public RMIClient()
    {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
        try {
        	  String dd=null;
			HttpSession session=request.getSession(false);
       
		//Session values for the room table
			String tempbooking_id=session.getAttribute("book_Id").toString();
			int booking_id=Integer.parseInt(tempbooking_id)+1;
			String tempid=session.getAttribute("roomId").toString();
			 int dbid=Integer.parseInt(tempid);		 
			String dbname=session.getAttribute("userName").toString();	
			String dbcheckIn=session.getAttribute("checkIn").toString();	
			String dbcheckOut=session.getAttribute("checkOut").toString();
			//String dbroomType=session.getAttribute("roomType").toString();
			String tempdbperson=session.getAttribute("person").toString();
			int dbperson=Integer.parseInt(tempdbperson);
			String tempdbnoRoom=session.getAttribute("noRoom").toString();
			int dbnoRoom=Integer.parseInt(tempdbnoRoom);
			String tempdbcost=session.getAttribute("cost").toString();
			float dbcost=Float.parseFloat(tempdbcost);
			
			
			//Session values for the services
			String str1=(String)session.getAttribute("str1");
			String str2=(String)session.getAttribute("str2");
			String str3=(String)session.getAttribute("str3");
			String str4=(String)session.getAttribute("str4");
			String str5=(String)session.getAttribute("str5");
			String name=(String)session.getAttribute("userName");
			
			
			
			ServletContext cxt=getServletContext();
			connection = (Connection) cxt.getAttribute("global");
			
			
			
			stSelect1=connection.prepareStatement("select to_char((sysdate),'dd-MON-yy') from dual");
			ResultSet result=stSelect1.executeQuery();
			if(result.next())
			{
			   dd=result.getString(1);
				System.out.println(result.getString(1));
			}
			
			long cardNo=Long.parseLong(request.getParameter("txtCardNo"));
			
			
			int pin=Integer.parseInt(request.getParameter("txtPin"));
			
			
			float tc=(Float)session.getAttribute("tc");
			System.out.println(tc);
			
			System.out.println(pin);
			System.out.println(cardNo);
			//System.out.println(request.getParameter("txtAmt"));
			
 //  float amt=Float.parseFloat(request.getParameter("txtAmt"));
			float amt=tc;
			
			System.out.println(amt);
			System.out.println(pin);
			System.out.println(cardNo);
			
			
			//Registry registry = LocateRegistry.getRegistry();
			try {
				Registry registry = LocateRegistry.getRegistry();
				CardValidator remote =(CardValidator)registry.lookup("CardService");
				//CardValidator remote =(CardValidator)Naming.lookup("CardService");
				System.out.println(remote);
				boolean flag = remote.validateCard(cardNo,amt,pin);
				if(flag)
				{
					System.out.println("Accepted");
				    stSelect=connection.prepareStatement("insert into room_booking values(?,?,?,?,?,?,?,?,?,?)");
				   
				    stSelect.setInt(1,booking_id );
				    System.out.println(booking_id);
				    
				    stSelect.setInt(2,dbid );
				    System.out.println(dbid);
				    stSelect.setString(3,dbname );
				    System.out.println(dbname);
				    
				    stSelect.setString(4,dd);
				    System.out.println(dd);
				    
				    stSelect.setInt(5,dbnoRoom );
				    System.out.println(dbnoRoom);
				    
				    stSelect.setString(6,dbcheckIn );
				    System.out.println(dbcheckIn);
				    
				    stSelect.setString(7,dbcheckOut );
				    System.out.println(dbcheckOut);
				    
				    stSelect.setFloat(8,dbcost );
				    System.out.println(dbcost);
				    
				    stSelect.setInt(9,dbperson );
				    System.out.println(dbperson);
				    
				   stSelect.setFloat(10,cardNo );
				  //  System.out.println(dbperson);
				    
				    
				    stSelect.executeUpdate();
				    System.out.println("Inserted the values in the database for rooms");
				    
				    
				    int totalroom=0;
				    int updatedroom=0;
				    stSelect3=connection.prepareStatement("select totalrooms from room where roomid=?");
				    stSelect3.setInt(1, dbid);
				    ResultSet res=stSelect3.executeQuery();
				    
				    if(res.next())
				    {
				    	totalroom=Integer.parseInt(res.getString(1));
				    }
				     updatedroom=totalroom-dbnoRoom;
				    
				    stSelect2=connection.prepareStatement("update room set totalrooms=? where roomid=?");
				    stSelect2.setInt(1, updatedroom);
				    stSelect2.setInt(2, dbid);
				    stSelect2.executeUpdate();
				    
				    System.out.println("Room updated");
				    
				    
				    stSelect1=connection.prepareStatement("insert into user_service values(SQ1.nextval,?,?,?,?,?,?)");
				    
				    stSelect1.setString(1,name);
				    stSelect1.setString(2,str1);
				    stSelect1.setString(3,str2);
				    stSelect1.setString(4,str3);
				    stSelect1.setString(5,str4);
				    stSelect1.setString(6,str5);

				    stSelect1.executeUpdate();
				    System.out.println("Inserted the values in the database for services");
				    
				    
				    //to insert values into transaction table
				    
				    txInsert =connection.prepareStatement("insert into transaction values(?,?,?,sysdate,?)");
				    
				    txInsert.setInt(1, booking_id);
				    txInsert.setString(2, dbname);
				    txInsert.setLong(3, cardNo);
				    txInsert.setFloat(4, dbcost);
				    
				    txInsert.executeUpdate();
				    
				    System.out.println("values inserted into transaction");//finally i have the card no to refund
					   
				    
				    
				    
				    
				    
                    response.sendRedirect("BookingConfirmation.jsp");
				}
				else
					System.out.println("Denied");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
