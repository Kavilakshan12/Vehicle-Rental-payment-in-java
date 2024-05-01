package web;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PaymentDAO;
import model.Payment;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")


public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentDAO PaymentDAO;
	
	public void init() {
		PaymentDAO = new PaymentDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertPayment(request, response);
				break;
			case "/delete":
				deletePayment(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updatePayment(request, response);
				break;
	
			default:
				listPayment(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}

	private void listPayment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Payment> listPayment = PaymentDAO.selectAllPayment();
		request.setAttribute("listPayment", listPayment);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Payment-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Payment-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Payment existingPayment = PaymentDAO.selectPayment(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Payment-form.jsp");
		request.setAttribute("Payment", existingPayment);
		dispatcher.forward(request, response);

	}

	private void insertPayment(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    String email = request.getParameter("Email");
	    String uname = request.getParameter("Uname");
	    double payAmount = Double.parseDouble(request.getParameter("PayAmount"));
	    String typeOfPayment = request.getParameter("TypeOfPayment");
	    
	    Payment newPayment = new Payment(email, uname, payAmount, typeOfPayment);
	    PaymentDAO.insertPayment(newPayment);
	    response.sendRedirect("list"); // Redirect to the appropriate page
	}


	private void updatePayment(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String email = request.getParameter("Email");
	    String uname = request.getParameter("Uname");
	    double payAmount = Double.parseDouble(request.getParameter("PayAmount"));
	    String typeOfPayment = request.getParameter("TypeOfPayment");
	    
	    Payment updatedPayment = new Payment(id, email, uname, payAmount, typeOfPayment);
	    PaymentDAO.updatePayment(updatedPayment);
	    response.sendRedirect("list"); // Redirect to the appropriate page
	}



	private void deletePayment(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
	    PaymentDAO.deletePayment(id);
	    response.sendRedirect("list"); // Redirect to the appropriate page
	}
	
}
