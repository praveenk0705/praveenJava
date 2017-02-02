package fi.pku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Entry {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement stStatement=null;
		ResultSet result=null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","praveen","admin");
			
			stStatement=connection.prepareStatement("select * from emp");
			result=stStatement.executeQuery();
			while(result.next()){
				System.out.println(result.getString("Ename"));
			}
			System.out.println("success");
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//log("Datdabase connection failed " ,e);
		}
		finally
		{
			try
			{
				if(result!=null)
					result.close();
				if(connection!=null)
					connection.close();
				if(stStatement!=null)
					stStatement.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
