package fi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardValidator extends Remote
{
	public boolean validateCard(long cardNo, float amount,int pin)throws RemoteException;
	public boolean CancelBooking(long cardNo,float refundAmt) throws RemoteException;
}

