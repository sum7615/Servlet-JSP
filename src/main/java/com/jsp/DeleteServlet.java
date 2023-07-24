package com.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1301103770830786428L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 1) {
			String idParameter = pathParts[1];
			int id = Integer.parseInt(idParameter);
			try (Connection con = RegistrationServlet.con()) {
				PreparedStatement pst = con.prepareStatement("delete from users where id =?");
				pst.setInt(1, id);
				int  rowCount = pst.executeUpdate();
				if (rowCount>0) {
					req.setAttribute("status", "success");
					req.setAttribute("successMessage", "User Deleted Successfully");					
				} else {
                    req.setAttribute("status", "failed");
    				req.setAttribute("errorMessage", "User not found or could not be deleted!");
				}
				Thread.sleep(2000);
			} catch (ClassNotFoundException | SQLException | NumberFormatException | InterruptedException e1) {
				e1.printStackTrace();
				 req.setAttribute("status", "failed");
 				 req.setAttribute("errorMessage", "An error occurred while processing the request!");
			}
		} else {
			req.setAttribute("status", "failed");
			req.setAttribute("errorMessage", "User ID is missing from the request!");
		}
		String url = req.getContextPath()+"/view-user";
		resp.sendRedirect(url);
	}
}
