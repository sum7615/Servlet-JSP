package com.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -5884991299676508608L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispacher = null;
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("status", "success");
			request.setAttribute("successMessage", "Loged out Successfully");
			dispacher = request.getRequestDispatcher("/index.jsp");
		}catch (Exception e) {
			request.setAttribute("status", "failed");
			request.setAttribute("errorMessage", "Couldn't logged you out! Error occured");
			dispacher = request.getRequestDispatcher("/welcome.jsp");
		}
		dispacher.forward(request, response);
	}
}
