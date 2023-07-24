package com.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ViewServlet extends HttpServlet{
	private static final long serialVersionUID = 8667736205766217998L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uname=null;
		String name=null;
		String id=null;
		String role=null;
		
		if(session!=null) {
//			Collecting session data
			 uname = (String) session.getAttribute("uname");
			 id = (String) session.getAttribute("id");
			 name = (String) session.getAttribute("name");
			 role = (String) session.getAttribute("role");
		}
		try (Connection con = RegistrationServlet.con()) {
			PreparedStatement pst = con.prepareStatement("select * from users");
			ResultSet result = pst.executeQuery();
			req.setAttribute("resultSet", result);
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			req.setAttribute("status", "failed");
			req.setAttribute("errorMessage", "No User found");
		}
		getServletContext().getRequestDispatcher("/view.jsp").include(req, resp);
	}

}
