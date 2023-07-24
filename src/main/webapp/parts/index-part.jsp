<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	
	List<Map> cardData = new ArrayList<Map>();
	
	// Servlet
	Map<String, String> servlet= new HashMap<String,String>();
	servlet.put("title", "Servlet");
	servlet.put("card-text", "Used to handle GET and POST requested accepted by <b>doGet()</b> and <b>doPost()</b> method");
	servlet.put("link", "images/getpost.jpg");
	cardData.add(servlet);
	
	// JSP
	Map<String, String> jsp= new HashMap<String,String>();
	jsp.put("title", "JSP");
	jsp.put("card-text", "Used <b>Decentralized JSP Components </b> to <b>generate dynamic response</b> for everry request recived at Servlet");
	jsp.put("link", "images/jsp.jpg");
	cardData.add(jsp);
	
	// JDBC
	Map<String, String> jdbc= new HashMap<String,String>();
	jdbc.put("title", "JDBC");
	jdbc.put("card-text", "Used to make <b>connection with Database</b> and <b>execute query command.</b>");
	jdbc.put("link", "images/jdbc.jpg");
	cardData.add(jdbc);
	
	// Client Side Validation
	
	Map<String, String> clientValidation= new HashMap<String,String>();
	clientValidation.put("title", "Client Side Validation");
	clientValidation.put("card-text", "Used for <b>Sign up</b> and <b>Sign in</b> form client side validation.");
	clientValidation.put("link", "images/client_val.jpg");
	cardData.add(clientValidation);
	
	// Server Side validation
	
	Map<String, String> serverValidation= new HashMap<String,String>();
	serverValidation.put("title", "Server Side Validation");
	serverValidation.put("card-text", "Used for <b>Sign up</b> and <b>Sign in</b> form Server side validation.");
	serverValidation.put("link", "images/server_val.jpg");
	cardData.add(serverValidation);
	
	// Using properties file
	Map<String, String> exceptionHandling= new HashMap<String,String>();
	exceptionHandling.put("title", "Using properties file");
	exceptionHandling.put("card-text", "Used <b>application.prperties</b> to get db details.");
	exceptionHandling.put("link", "images/properties.jpg");
	cardData.add(exceptionHandling);
	
	// Session
	Map<String, String> sessionData= new HashMap<String,String>();
	sessionData.put("title", "Session");
	sessionData.put("card-text", "Used Session to access user data for  <b>Multipage Navigation.</b>");
	sessionData.put("link", "images/session.jpg");
	cardData.add(sessionData);
	
	// Java features
	Map<String, String> features= new HashMap<String,String>();
	features.put("title", "Java features");
	features.put("card-text", "Used <b>Collection</b> to perform batch operation");
	features.put("link", "images/collection.jpg");
	cardData.add(features);
	
	//Tomcat
	 Map<String, String> tomcat= new HashMap<String,String>();
	 tomcat.put("title", "Tomcat Server");
	 tomcat.put("card-text", "Used <b>Tomcat</b> as server to run the application as web-app");
	 tomcat.put("link", "images/tomcat.jpg");
	cardData.add(tomcat);
	
	// Authentication & Authorization
	// Map<String, String> auth= new HashMap<String,String>();
	// auth.put("title", "Authentication & Authorization");
	// auth.put("card-text", "Perform Log in operation using session, Restrict access for user, give priviliage to admin");
	// auth.put("link", "images/signup-image.jpg");
	// cardData.add(auth);
	
	// Role based access
	Map<String, String> role= new HashMap<String,String>();
	role.put("title", "Role based access");
	role.put("card-text", "Give access according to role");
	role.put("link", "images/rolebased_access.jpg");
	cardData.add(role);
	
	// Frontend library
	
	Map<String, String> frontendLibrary= new HashMap<String,String>();
	frontendLibrary.put("title", "Frontend library");
	frontendLibrary.put("card-text", "Used frontend library Bootstrap. Besides that we used material and Google icon");
	frontendLibrary.put("link", "images/frontend_library.jpg");
	cardData.add(frontendLibrary);
	
	
	
%>