package com.bill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class billModel {
	
	//Creating the DB connection
		public Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/egsystem", "root", "2057060");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}	
	
}
