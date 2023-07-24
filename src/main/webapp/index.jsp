<%@page import="com.jsp.Util"%>
<%@include file="/parts/header.jsp"%>
<%@include file="/parts/index-part.jsp"%>
<title>Home</title>
</head>
<body id="page-top"  class="home"></body>
 <% Util.createDbOnce(); %>
	<%@include file="/parts/nav.jsp"%>
	<div class="home-h1">Technologies and Features</div>
	<div class="home-body">
	<%	for(Map<String,String> data: cardData){ %>
	<div class="one-card paddin-1">
		<div class="card" style="width: 18rem; height:13rem">
			<div class="card-body">
			  <h5 class="card-title" style="text-align: center;"><%=data.get("title") %></h5>
			  <p class="card-text"><%=data.get("card-text") %></p>
				<div class="paddin-1">
					<a href="<%=request.getContextPath()%>/<%=data.get("link") %>" class="card-link">Example</a>
			   </div>
			</div>
		  </div>
	</div>
	
	<%
		}
	%>
	</div>
	
	
<%@include file="/parts/footer.jsp"%>
