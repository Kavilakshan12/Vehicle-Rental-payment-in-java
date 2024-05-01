package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Payment;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class PaymentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/user?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "dilax2001";

	private static final String INSERT_Payment_SQL = "INSERT INTO Payment" + "  (Email,Uname,PayAmount,TypeOfPayment) VALUES "
			+ " (?,?,?,?);";

	private static final String SELECT_Payment_BY_id = "select id,Email,Uname,PayAmount,TypeOfPayment from Payment where id =?";
	private static final String SELECT_ALL_Payment = "select * from Payment";
	private static final String DELETE_Payment_SQL = "delete from Payment where id = ?";
	private static final String UPDATE_Payment_SQL = "update Payment set Email =?,Uname= ?,PayAmount= ?,TypeOfPayment= ? where id = ?";

	//public PaymentDAO() {
	//}

	protected Connection getConnection() {
	    Connection connection = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    } catch (SQLException e) {
	    	
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return connection;
	}


	public void insertPayment(Payment Payment) throws SQLException {
		System.out.println(INSERT_Payment_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Payment_SQL)) {
			
			preparedStatement.setString(1, Payment.getEmail());
			preparedStatement.setString(2, Payment.getUname());
			preparedStatement.setDouble(3, Payment.getPayAmount());
			preparedStatement.setString(4, Payment.getTypeOfPayment());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Payment selectPayment(int id) {
	    Payment Payment = null; // Changed variable name to "payment"
	    // Step 1: Establishing a Connection
	    try (Connection connection = getConnection();
	        // Step 2: Create a statement using a connection object
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Payment_BY_id);) {
	        preparedStatement.setInt(1, id);
	        System.out.println(preparedStatement);
	        // Step 3: Execute the query or update query
	        ResultSet rs = preparedStatement.executeQuery();

	        // Step 4: Process the ResultSet object.
	        while (rs.next()) {
	        	//String Email = rs.getString("Email");
	        	
	        	String Email = rs.getString("Email");
	        	String Uname = rs.getString("Uname");
	            double PayAmount = rs.getDouble("PayAmount"); 
	            String typeOfPayment = rs.getString("TypeOfPayment");
	            Payment = new Payment(id,Email,Uname,PayAmount,typeOfPayment);
	        }
	    } catch (SQLException e) {
	    	printSQLException(e);
	    }
	    return Payment;
	}



	public List<Payment> selectAllPayment() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Payment> Payment = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Payment);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String Email = rs.getString("Email"); 
				String Uname = rs.getString("Uname");
	            double payAmount = rs.getDouble("PayAmount"); 
	            String typeOfPayment = rs.getString("TypeOfPayment");
				
	            
	            Payment.add(new Payment(id,Email,Uname,payAmount,typeOfPayment));
			}
		} catch (SQLException e) {
			
			printSQLException(e);
		}
		
		
		return Payment;
	}

	public boolean deletePayment(int id) throws SQLException {
		boolean rowDeleted ;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Payment_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updatePayment(Payment Payment)throws SQLException {
		boolean rowUpdated ;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_Payment_SQL);) {
			
			statement.setString(1, Payment.getEmail());
			statement.setString(2, Payment.getUname());
			statement.setDouble(3, Payment.getPayAmount());
			statement.setString(4, Payment.getTypeOfPayment());
			statement.setInt(5, Payment.getid());
			

			rowUpdated = statement.executeUpdate() > 0;
			
		}
		return rowUpdated;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}



}
