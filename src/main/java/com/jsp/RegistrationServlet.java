package com.jsp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

import com.jsp.exception.CustomException;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = -7403397167239757286L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/registration.jsp").include(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("name");
		String upwd = request.getParameter("pass");
		String uemail = request.getParameter("email");
		int umobile = Integer.parseInt(request.getParameter("contact"));
		String name = request.getParameter("u_name");
		String date = request.getParameter("dob");
		Date dob = Date.valueOf(date);
		
		Map<Boolean, String> validateInput = isValidPass(upwd, uemail, umobile);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		if (validateInput.keySet().stream().findFirst().get()) { 
			try {
				try (Connection con = RegistrationServlet.con()) {
					PreparedStatement pst = con.prepareStatement(
							"INSERT INTO users (uname, upwd, uemail, umobile, name, dob)VALUES (?, ?, ?, ?, ?, ?)");
					pst.setString(1, uname);
					pst.setString(2, upwd);
					pst.setString(3, uemail);
					pst.setInt(4, umobile);
					pst.setString(5, name);
					pst.setDate(6, dob);

					int c = pst.executeUpdate();
					if (c > 0) {
						request.setAttribute("status", "success");
						request.setAttribute("successMessage", "User Created Successfully");
					} else {
						request.setAttribute("status", "failed");
						request.setAttribute("errorMessage", "Have you given all data?");
					}
				}
			} catch (PSQLException e) {
				request.setAttribute("status", "failed");
				request.setAttribute("errorMessage", "Duplicate user name and email found");
			} catch (Exception e) {
				request.setAttribute("status", "failed");
				String errorMessage = "An error occurred: " + e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
			}
		} else {
			request.setAttribute("status", "failed");
			request.setAttribute("errorMessage", "Some error occured");
		}
		dispatcher.forward(request, response);
		
	}

	public static Connection con() throws ClassNotFoundException, SQLException, IOException {
		InputStream fis = Util.class.getClassLoader().getResourceAsStream("application.properties");
		Properties p = new Properties();
		p.load(fis);
		String url1 = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url1, username, password);
	}

	public Map<Boolean, String> isValidPass(String password, String email, int contact) {
		String passPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
		String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		String contactPattern = "^\\d{10}$";

		Pattern pass_pattern = Pattern.compile(passPattern);
		Pattern email_pattern = Pattern.compile(emailPattern);
		Pattern contact_pattern = Pattern.compile(contactPattern);

		String contacts = "" + contact;

		Matcher passMatcher = pass_pattern.matcher(password);
		Matcher emailMatcher = email_pattern.matcher(email);
		Matcher contactMatcher = contact_pattern.matcher(contacts);
		Map<Boolean, String> resul = new HashMap<>();

		if (passMatcher.matches() && emailMatcher.matches() && contactMatcher.matches()) {
			resul.put(true, "Validated data");
			return resul;
		} else {
			resul.put(false, "Error in data");
			return resul;
		}
	}

}
