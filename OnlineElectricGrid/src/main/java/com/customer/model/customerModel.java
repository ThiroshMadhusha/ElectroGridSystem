package com.customer.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class customerModel {

	
	//Creating the DB connection
	public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "9909");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertCustomer(String name, String address, String phone, String email, String nic )
	{
		String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 	{return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 
		 
		 String query = " insert into customer (cusID,`cusName`,`cusAddress`,`cusPhoneNo`,`cusEmail`, `cusNIC` )"
		 + " values ( ?, ?, ?, ?, ?, ? )";
		 
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, phone);
		 preparedStmt.setString(5, email);
		 preparedStmt.setString(6, nic);
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Customer Added successfully";
		 }
		
		catch (Exception e)
		{
			output = "Error occur during inserting\n";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
//	read customer details
	
	
	public String readCustomer() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the HTML table to be displayed
			output = "<table border='1'>"
					+ "<tr><th>ID</th>"
					+ "<th>Name</th>" 
					+ "<th>Address</th>" 
					+ "<th>Phone</th>"
					+ "<th>Address</th>"
					+"<th>NIC</th>";
			
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while(rs.next()) {
				
				String cusID = Integer.toString(rs.getInt("cusID"));
				String cusName = rs.getString("cusName");
				String cusAddress = rs.getString("cusAddress");
				String cusPhoneNo = rs.getString("cusPhoneNo");
				String cusEmail = rs.getString("cusEmail");
				String cusNIC = rs.getString("cusNIC");
				
				// Add into the HTML table
				
				output += "<tr><td>" + cusID + "</td>";
				output += "<td>" + cusName + "</td>";
				output += "<td>" + cusAddress + "</td>";
				output += "<td>" + cusPhoneNo + "</td>";
				output += "<td>" + cusEmail + "</td>";
				output += "<td>" + cusNIC + "</td>";
				
				// buttons
				//output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				//+ "<td><form method='post' action=''>"
				//+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				//+ "<input name='cus_id' type='hidden' value='" + cusID
				//+ "'>" + "</form></td></tr>";
				
			}
			 con.close();
			 // Complete the HTML table
			 
			 output += "</table>";
			
		}catch(Exception e) {
			
			output = "Error while reading the details of customer";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	

	
	// Update Customer 
	
	public String updateCustomer(String ID, String name, String address, String phone, String email, String nic ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
			
			
			
			// create a prepared statement
			String query = "UPDATE customer SET cusName=?,cusAddress=?,cusPhoneNo=?,cusEmail=?,cusNIC=? WHERE cusID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, nic);
			preparedStmt.setInt(6,Integer.parseInt(ID));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			   
		}catch(Exception e) {
			
			output = "Error while updating the billing details.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// Delete Customer 
	
	public String deleteCustomer(String cusID){
		
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from cutomer where cusID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1,  Integer.parseInt(cusID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
		}
		catch (Exception e)
		{
			output = "Error while deleting the customer";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	

		

}