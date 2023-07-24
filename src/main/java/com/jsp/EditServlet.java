package com.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.exception.CustomException;

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 8126030539370589785L;
	int id;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathInfo = req.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idParameter = pathParts[1];
			id = Integer.parseInt(idParameter);
			try (Connection con = RegistrationServlet.con()) {
				PreparedStatement pst = con.prepareStatement("select * from users where id =?");
				pst.setInt(1, id);
				ResultSet result = pst.executeQuery();
				if (result.next()) {
					req.setAttribute("user", result);
					getServletContext().getRequestDispatcher("/edit.jsp").include(req, resp);
				} else {
					getServletContext().getRequestDispatcher("/view.jsp").include(req, resp);
				}
			} catch (ClassNotFoundException | SQLException | NumberFormatException e1) {
				e1.printStackTrace();
			}

		} else {
			getServletContext().getRequestDispatcher("/view.jsp").include(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("name");
		String uemail = req.getParameter("email");
		int umobile = Integer.parseInt(req.getParameter("contact"));
		String name = req.getParameter("u_name");
		String date = req.getParameter("dob");
		Date dob = Date.valueOf(date);
		
		Map<Boolean, String> validateInput = isValidPass(uemail, umobile);

		if (validateInput.keySet().stream().findFirst().get()) { 
			try (Connection con = RegistrationServlet.con()) {
				PreparedStatement qst = con
						.prepareStatement("UPDATE users SET uname=?, uemail=?, umobile=?, name=?,dob=? WHERE id=?");
				qst.setString(1, uname);
				qst.setString(2, uemail);
				qst.setInt(3, umobile);
				qst.setString(4, name);
				qst.setInt(6, id);
				qst.setDate(5, dob);
				qst.executeUpdate();
				qst.close();
			} catch (SQLException | ClassNotFoundException e) {
				throw new CustomException(e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/view-user");
		}else {
			throw new CustomException(validateInput.values().stream().findFirst().get());
		}
	}

	public Map<Boolean, String> isValidPass(String email, int contact) {
		String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		String contactPattern = "^\\d{10}$";

		Pattern email_pattern = Pattern.compile(emailPattern);
		Pattern contact_pattern = Pattern.compile(contactPattern);

		String contacts = ""+contact;
		
		Matcher emailMatcher = email_pattern.matcher(email);
		Matcher contactMatcher = contact_pattern.matcher(contacts);
		
		Map<Boolean, String> resul = new HashMap<>();

		if (!emailMatcher.matches()) {
			resul.put(false, "Error in email data ");
			return resul;
		} else if(!contactMatcher.matches()) {
			resul.put(false, "Error in contact data ");
			return resul;
		}	
		else {
			resul.put(true, "Input Validated");
			return resul;
		}
	}
}
