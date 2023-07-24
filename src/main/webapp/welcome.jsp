<%@page import="java.util.Objects"%>
<%@include file="/parts/header.jsp"%>
<title>Login success</title>
</head>
<body>
	<%@include file="/parts/nav.jsp"%>
	<div class="main">
		<section class="sign-in">
			<div class="container">
				<%
				String userName1 = Objects.toString(session.getAttribute("name"), "").trim();
				String userRole1 = Objects.toString(session.getAttribute("role"), "").trim();
				if (userName1 != null ) {
				%>
				<div class="signin-content">
					<h1 class="welcome_messegae">
						Welcome
						<%=userName1%>
					</h1>

				</div>
				<%
				if(userRole1.equals("admin")){
				%>
				<div class="all-user-button">
					<a href="<%=request.getContextPath()%>/view-user" class="btn">All
						users</a>
				</div>
				<%
					}
				%>
				<%
				} else {
				%>
				<h1 class="welcome_messegae">You doesn't have access for this
					page</h1>
				<%
				}
				%>
			</div>
		</section>
	</div>
	<%@include file="/parts/footer.jsp"%>