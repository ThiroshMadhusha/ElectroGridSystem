package com.bill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class billModel {
	
		//	Creating the DB connection
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
		
		
		//	read bill details
		
		
		public String readBill() {
			
			String output = "";
			
			try {
				
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the HTML table to be displayed
				output = "<table border='1'>"
						+ "<tr><th>Bill ID</th>" 
						+ "<th>Account NO</th>" 
						+ "<th>C_Name</th>" 
						+ "<th>Address</th>" 
						+ "<th>billNo</th>" 
//						+ "<th>C_Read</th>"
						+"<th>Unit</th>"
//						+"<th>P_Amount</th>"
//						+"<th>C_Amount</th>"
						+"<th>T_Amount</th>"
						+"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from bills";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while(rs.next()) {
					
					String billID = Integer.toString(rs.getInt("billID"));
					String billAccountNo = rs.getString("billAccountNo");
					String billName = rs.getString("billName");
					String billAddress = rs.getString("billAddress");
					String billNo = rs.getString("billNo");
//					String billcRead = rs.getString("billcRead");
					String billUnit = rs.getString("billUnit");
//					String billpAmmount = rs.getString("billpAmmount");
//					String billcAmmount = rs.getString("billcAmmount");
					String billtAmount = rs.getString("billtAmount");
					
					// Add into the HTML table
					
					output += "<tr><td>" + billID + "</td>";
					output += "<td>" + billAccountNo + "</td>";
					output += "<td>" + billName + "</td>";
					output += "<td>" + billAddress + "</td>";
					output += "<td>" + billNo + "</td>";
//					output += "<td>" + billcRead + "</td>";
					output += "<td>" + billUnit + "</td>";
//					output += "<td>" + billpAmmount + "</td>";
//					output += "<td>" + billcAmmount + "</td>";
					output += "<td>" + billtAmount + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
					+ "<td><form method='post' action=''>"
					+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
					+ "<input name='cus_id' type='hidden' value='" + billID
					+ "'>" + "</form></td></tr>";
					
				}
				 con.close();
				 // Complete the HTML table
				 
				 output += "</table>";
				
			}catch(Exception e) {
				
				output = "Error while reading the bill betails of users";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		

	
}
