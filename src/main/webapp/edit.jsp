<%@include file="/parts/header.jsp"%>
<title>Edit User</title>
</head>
<body>
	<%@include file="/parts/nav.jsp"%>
	<%
	java.sql.ResultSet user = (java.sql.ResultSet) request.getAttribute("user");
	%>
	<div class="main">
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Edit User</h2>
						<form method="post" action="edit-user" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name"
									value="<%=user.getString("uname")%>" />
							</div>
							<div class="form-group">
								<label for="u_name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="u_name" id="u_name"
									value="<%=user.getString("name")%>" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email"
									value="<%=user.getString("uemail")%>" />
							</div>

							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="contact" id="contact"
									value="<%=user.getString("umobile")%>" />
							</div>
							<div class="form-group">
								<label for="dob"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="date" name="dob" id="dob"
									value="<%=user.getString("dob")%>" />
							</div>
							<div style="display: flex;">
								<div class="form-group form-button">
									<input type="submit" name="signup" 
										class="form-submit" value="Update" />
								</div>
								<div>
									<a href="<%=request.getContextPath()%>/view-user"
										class="form-submit"
										style="margin-left: 1rem; text-decoration: none; color: inherit;">Back</a>
								</div>
							</div>

						</form>

					</div>

				</div>
			</div>
		</section>
	</div>
	<%@include file="/parts/footer.jsp"%>