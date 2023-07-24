package com.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = -8876169244830298912L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").include(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String upwd = request.getParameter("password");
		RequestDispatcher dispacher = null;
		try (Connection con = RegistrationServlet.con()){
			PreparedStatement pst = con.prepareStatement("select id,uname,name,role from users where uname=? and upwd =?");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			ResultSet result=  pst.executeQuery();
			if(result.next()) {
				String name = result.getString("name");
				
//				Starting session
				HttpSession session = request.getSession();
		
//				Setting session attributes
				session.setAttribute("uname", uname);
				session.setAttribute("role", result.getString("role"));
				session.setAttribute("name", name);
				session.setAttribute("id", result.getString("id"));
//				Setting request custom attributes
				request.setAttribute("name",name);
				request.setAttribute("status", "success");
				request.setAttribute("successMessage", "Loged in Successfully");
				
//				Creating dispacher object 
				dispacher = request.getRequestDispatcher("/welcome.jsp");
			}else {
				request.setAttribute("status", "failed");
				request.setAttribute("errorMessage", "Wrong username and password");
				dispacher = request.getRequestDispatcher("login.jsp");
				dispacher.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("status", "failed");
			String errorMessage = "An error occurred: " + e.getMessage();
			request.setAttribute("errorMessage", errorMessage);
			e.printStackTrace();
			dispacher = request.getRequestDispatcher("login.jsp");
			dispacher.include(request, response);
		}
		dispacher.include(request, response);
	}

}
