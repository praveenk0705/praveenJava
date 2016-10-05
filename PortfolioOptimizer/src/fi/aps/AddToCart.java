package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String stockIndex = request.getParameter("stockIndex");
		String stockName = request.getParameter("stockName");
		String stockPrice = request.getParameter("stockPrice");
		String noOfStocks = request.getParameter("noOfStocks");
		System.out.println("inside add to cart");

		Float priceOfStock = Float.parseFloat(stockPrice);
		Float intNoOfStocks = Float.parseFloat(noOfStocks);
		Float totalPrice = priceOfStock * intNoOfStocks;
		System.out.println(stockIndex + stockName + stockPrice + noOfStocks);

		Connection connection = null;
		ResultSet result = null;
		ResultSet getCashBalanceResult = null;

		PreparedStatement stSelect = null;
		PreparedStatement stSelect2 = null;
		PreparedStatement getcashBalanceStatement = null;

		try {
			ServletContext context = getServletContext();
			connection = (Connection) context.getAttribute("globalConnection");

			stSelect = connection
					.prepareStatement("INSERT INTO ADDTOCART VALUES (?,?,?,?,?)");
			stSelect2 = connection
					.prepareStatement("update person set balance = balance - ?");
			getcashBalanceStatement = connection
					.prepareStatement("select balance from person");

			stSelect2.setFloat(1, totalPrice);
			stSelect.setString(1, stockIndex);
			stSelect.setString(2, stockName);
			stSelect.setFloat(3, totalPrice);
			stSelect.setFloat(4, priceOfStock);
			stSelect.setFloat(5, intNoOfStocks);

			getCashBalanceResult = getcashBalanceStatement.executeQuery();

			float cashBalance = 0;

			while (getCashBalanceResult.next())
				cashBalance = getCashBalanceResult.getFloat(1);
			System.out.println(cashBalance);

			if (cashBalance >= totalPrice) {

				result = stSelect.executeQuery();
				stSelect2.executeQuery();
				connection.commit();

				if (result.next()) {
					out.print("Your transaction completed successfully");
				}
			}
			else{
				out.print("Your account does not have sufficient money");
			}
			
			
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
