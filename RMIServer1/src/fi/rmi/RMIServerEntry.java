package fi.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIServerEntry 
{

	public static void main(String[] args)
	{
		try 
		{
			CardValidationImpl cvi=new CardValidationImpl();
			
			Naming.bind("CardService", cvi);
			
			System.out.println("Service Bound");
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (RemoteException e) 
		{
			
			e.printStackTrace();
		} 
		catch (AlreadyBoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}

}
