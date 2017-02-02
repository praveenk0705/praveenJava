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


public class feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection = null;
	PreparedStatement stInsert = null;
	PreparedStatement stSelect = null;

	String fname;

	public feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext cxt = getServletContext();
		HttpSession session = request.getSession(false);
		connection = (Connection) cxt.getAttribute("global");
		if (connection == null)
			System.out.println("connection not present.....");

		try {
			String subject = request.getParameter("Subject");
			String feedback = request.getParameter("feed");

			stSelect = connection
					.prepareStatement("select firstname from registration where username=?");

			if (session != null) {
				String user = (String) (session.getAttribute("userName"));
				if (user != null) {
					stSelect.setString(1, user);
					ResultSet result = stSelect.executeQuery();
					if (result.next()) {
						fname = result.getString(1);
					}
					System.out.println("true");
				}
			}

			stInsert = connection
					.prepareStatement("insert into feedback values(?,sysdate,?,?)");

			stInsert.setString(1, fname);

			stInsert.setString(2, subject);
			stInsert.setString(3, feedback);

			stInsert.executeUpdate();
			response.sendRedirect("Feedback.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
