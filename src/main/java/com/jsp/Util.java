package com.jsp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {
	 private static boolean dbCreated = false;

	    public static synchronized void createDbOnce() throws ClassNotFoundException, SQLException, IOException {
	        if (!dbCreated) {
	        	createDb();
	            dbCreated = true; 
	        }
	    }
	
	public static void createDb() throws SQLException, IOException, IOException, ClassNotFoundException {

		InputStream fis = Util.class.getClassLoader().getResourceAsStream("application.properties");
		Properties p = new Properties();
		p.load(fis);

		String url1 = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");

		String[] pp = url1.split("/");
		String dbName = pp[pp.length - 1];

		String serverUrl = url1.replaceAll(pp[pp.length - 1], "");

		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(serverUrl, username, password);
		Statement st = con.createStatement();
		String sqlquery = "SELECT datname FROM pg_database";
		ResultSet res = st.executeQuery(sqlquery);
		List<String> allDb = new ArrayList<String>();
		while (res.next()) {
			allDb.add(res.getString("datname"));
		}

		if (allDb.contains(dbName)) {
			System.out.println("DB already existed");
		} else {
			String sqlqueryToCreateDb = "CREATE DATABASE " + dbName;
			Connection conj = DriverManager.getConnection(serverUrl, username, password);
			
			
			
			Statement stc = conj.createStatement();
			stc.executeUpdate(sqlqueryToCreateDb);
			System.out.println("DB created");
			
			
			Connection conc = DriverManager.getConnection(url1, username, password);
			Statement stcg = conc.createStatement();
			
			String creareId = "CREATE SEQUENCE users_id_seq;";
			stcg.executeUpdate(creareId);
			
			
			String createEnum= "CREATE TYPE user_status_enum AS ENUM ('active', 'inactive', 'pending');";
			stcg.executeUpdate(createEnum);
			String createTableQuery = "CREATE TABLE IF NOT EXISTS public.users\r\n"
					+ "(\r\n"
					+ "    uname character varying(255) COLLATE pg_catalog.\"default\",\r\n"
					+ "    upwd character varying(255) COLLATE pg_catalog.\"default\",\r\n"
					+ "    uemail character varying(255) COLLATE pg_catalog.\"default\",\r\n"
					+ "    umobile bigint,\r\n"
					+ "    name character varying(255) COLLATE pg_catalog.\"default\",\r\n"
					+ "    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),\r\n"
					+ "    status user_status_enum DEFAULT 'active', -- No need for ::user_status_enum\r\n"
					+ "    role character(50) COLLATE pg_catalog.\"default\" DEFAULT 'user'::bpchar,\r\n"
					+ "    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "    dob date,\r\n"
					+ "    CONSTRAINT users_pkey PRIMARY KEY (id),\r\n"
					+ "    CONSTRAINT uq_uname_uemail UNIQUE (uname, uemail)\r\n"
					+ ");";
			stcg.executeUpdate(createTableQuery);
			conc.close();
			stcg.close();
		}
		con.close();
		st.close();
	}

	
}
