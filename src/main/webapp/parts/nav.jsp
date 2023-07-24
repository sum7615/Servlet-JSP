	<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/">Home</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">

					<%
		String userName = (String) session.getAttribute("uname");
		if (userName == null) {
		%>
				<li class="nav-item">
					<a class="nav-link"	href="<%=request.getContextPath()%>/login">Login</a>
				</li>
				
				<%
		} else {
		%>
			<li class="nav-item">
					<a class="nav-link"	href="<%=request.getContextPath()%>/logout">Logout</a>
			</li>
			<li class="nav-item">
					<a class="nav-link"	href="<%=request.getContextPath()%>/welcome">Dashboard</a>
			</li>
		<%
			} 
		%>
						<li class="nav-item">
					<a class="nav-link"	href="<%=request.getContextPath()%>/register">Create an account</a>
				</li>
				<li class="nav-item">
					<a class="nav-link"	href="<%=request.getContextPath()%>/view-user">All Users</a>
				</li>
			</ul>
		</div>
	</nav>