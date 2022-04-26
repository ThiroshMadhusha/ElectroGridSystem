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
		
		public String insertBill(String account_no, String name, String address, String bill_no, String units, String t_amount )
		{
			String output = "";
			try
			 {
			 Connection con = connect();
			 if (con == null)
			 	{return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 
			 
			 String query = " insert into bills (billID,`billAccountNo`,`billName`,`billAddress`,`billNo`, `billUnit`, `billtAmount`)"+" values ( ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, account_no);
			 preparedStmt.setString(3, name);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, bill_no);
			 preparedStmt.setString(6, units);
			 preparedStmt.setString(7, t_amount);
			 
			 // execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Bill Added successfully";
			 }
			
			catch (Exception e)
			{
				output = "Error occur during inserting";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
}
