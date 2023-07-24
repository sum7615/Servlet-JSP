<%@page import="java.util.Objects"%>
<%@include file="/parts/header.jsp"%>
<title>View Users</title>
</head>
<body>
	<%@include file="/parts/nav.jsp"%>
	<div class="container justify-content-center">
		<%
		String userRole = (String) session.getAttribute("role");
		userRole = Objects.toString(userRole, "").trim();
		if (userRole.equals("admin")) {
		%>

		<h1 class="text-center">List of users</h1>
		<table class="table table-hover">
			<thead>
				<tr class="table-secondary ">
					<th scope="col">Id</th>
					<th scope="col">Name</th>
					<th scope="col">Dob</th>
					<th scope="col">User Name</th>
					<th scope="col">Email</th>
					<th scope="col">Mobile</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				java.sql.ResultSet resultSet = (java.sql.ResultSet) request.getAttribute("resultSet");
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String uname = resultSet.getString("uname");
					String email = resultSet.getString("uemail");
					String mobile = resultSet.getString("umobile");
					String dob = resultSet.getString("dob");
				%>
				<tr>
					<td><%=id%></td>
					<td><%=name%></td>
					<td><%=dob%></td>
					<td><%=uname%></td>
					<td><%=email%></td>
					<td><%=mobile%></td>
					<td><a href="<%=request.getContextPath()%>/edit-user/<%=id%>"><span
							class="material-symbols-outlined"> edit </span></a> <a
						href="<%=request.getContextPath()%>/delete-user/<%=id%>"><span
							class="material-symbols-outlined"> delete_forever </span></a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<%
		if (userRole == null || !userRole.equals("admin")) {
		%>
		<h1>You don't have access of this page</h1>
		<%
		}
		%>
		<%
		}
		%>
		</div>
		<%@include file="/parts/footer.jsp"%>