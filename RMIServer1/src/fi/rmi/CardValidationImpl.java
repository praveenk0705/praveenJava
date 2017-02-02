package fi.rmi;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardValidationImpl extends UnicastRemoteObject implements
		CardValidator {
	Connection connection = null;
	PreparedStatement stSelect = null;
	PreparedStatement stSelect1 = null;
	PreparedStatement stUpdate = null;
	PreparedStatement stUpdate1 = null;
	PreparedStatement stCustBalance = null;

	float HotelAccBal = 0;
	float hotelBal = 0;
	float tempHotelbal = 0;

	public CardValidationImpl() throws RemoteException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@sdc:1521:oracle11", "user47",
					"shriganesh1");

			stCustBalance = connection
					.prepareStatement("select * from payment where cardno=?");

			// i dont need this for cancellation
			stSelect = connection
					.prepareStatement("select * from payment where cardNo=? and pin=?");

			stUpdate = connection
					.prepareStatement("update payment set balance=? where cardNo=?");

			// fetches me hotel balance
			stSelect1 = connection
					.prepareStatement("select balance from HotelAcc where accno=222");

			// Updates hotel account
			stUpdate1 = connection
					.prepareStatement("update hotelacc set balance=? where accno=222");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RemoteException("Failed to connect to Database", e);
		}
	}

	@Override
	public boolean validateCard(long cardNo, float amount, int pin)
			throws RemoteException {
		ResultSet result = null;

		try {
			stSelect.clearParameters();

			stSelect.setLong(1, cardNo);
			stSelect.setLong(2, pin);

			result = stSelect.executeQuery();
			if (result.next()) {
				float balance = result.getInt("balance");
				System.out.println(balance);

				if (balance > amount) {
					float netBalance = balance - amount;
					System.out.println(netBalance);

					System.out.println(amount);
					stUpdate.clearParameters();

					stUpdate.setFloat(1, netBalance);
					stUpdate.setLong(2, cardNo);

					stUpdate.executeUpdate();
					System.out.println("Hiii");

					ResultSet result1 = stSelect1.executeQuery();
					if (result1.next()) {
						tempHotelbal = result1.getFloat(1);
						System.out.println(tempHotelbal);
						hotelBal = tempHotelbal + amount;
						System.out.println(hotelBal);
					}

					stUpdate1.setFloat(1, hotelBal);
					stUpdate1.executeUpdate();

					System.out.println("hotelAccount updated");
					return true;
				}
			} else
				return false;

		} catch (SQLException e) {
			throw new RemoteException("Transaction Failed", e);

		}

		finally {
			try {
				if (result != null)
					result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean CancelBooking(long cardNo, float refundAmt)
			throws RemoteException {

		ResultSet result = null;

		try {
			result = stSelect1.executeQuery();
			if (result.next()) {
				float balance = result.getInt("balance");// hotel balance
				System.out.println(balance);

				if (balance > refundAmt) {
					float netbalance = balance - refundAmt;
					stUpdate1.setFloat(1, netbalance);
					stUpdate1.executeUpdate();
					System.out.println("Hotel account debited");

					stCustBalance.setLong(1, cardNo);

					ResultSet custBalanceRes = stCustBalance.executeQuery();

					if (custBalanceRes.next()) {
						float custBalance = custBalanceRes.getFloat("Balance");

						float netUserBalance = custBalance + refundAmt;

						stUpdate.setFloat(1, netUserBalance);
						stUpdate.setLong(2, cardNo);
						stUpdate.executeUpdate();

						System.out.println("User account credited");
						return true;
					}

				}

			} else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Failed..............");
		}
		return false;
	}

}
