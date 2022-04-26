package model;

import java.sql.*; 



public class PaymentHistory {

	//A common method to connect to the DB
	
			private Connection connect()
			 {
			 Connection con = null;
			 try
			 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //DB details: DBServer/DBName, user name, password
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payments", "root", "prasan123");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
	}
			public String readPaymentHistory() {
				
				
				{
					 String output = "";
					 try
					 {
					 Connection con = connect();
					 if (con == null)
						 
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table border='1'><tr><th> A/C Number </th><th> Name </th>" +
					 "<th> Payment Amount </th>" +
					 "<th>  Contact   </th></tr>";

					 
					 
					 String query = "select * from payments where paymentCode=?";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
					 String paymentID = Integer.toString(rs.getInt("paymentID"));
					 String paymentCode = rs.getString("paymentCode");
					 String paymentName = rs.getString("paymentName");
					 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
					 String paymentContact = Integer.toString(rs.getInt("paymentContact"));
				
					 
					 // Add into the html table
					 output += "<td>" + paymentCode + "</td>";
					 output += "<td>" + paymentName + "</td>";
					 output += "<td>" + paymentAmount + "</td>";
					 output += "<td>" + paymentContact + "</td>";
					 
					 output += "<td><input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'></td>"
							+ "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>"; 
					 
					 
			
					
					 }
					 con.close();
					 // Complete the html table
					 output += "</table>";
					 }
					 catch (Exception e)
					 {
					 output = "Error while reading the items.";
					 System.err.println(e.getMessage());
					 }
					 return output;
					 }
			}
}
