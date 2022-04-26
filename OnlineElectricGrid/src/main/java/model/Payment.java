package model;


import java.sql.*; 

public class Payment 
	
	{
		//A common method to connect to the DB
	
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //DB details: DBServer/DBName, user name, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "prasan123");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
}

		public String insertPayment(String billNo, String name, String amount, String contact)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 	{return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 
	 
	 String query = " insert into payments (paymentID,`paymentCode`,`paymentName`,`paymentAmount`,`paymentContact`,`adminRemark`)"
	 + " values ( ?,?, ?, ?, ?,?)";
	 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, billNo);
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(amount));
	 preparedStmt.setDouble(5,Double.parseDouble(contact));
	 preparedStmt.setString(6, "admin remark");
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Payment done successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while doing the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output; 

}

		public String readPayment() {
			
		
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

				 String query = "select * from payments";
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
				 output += "<tr><td>" + paymentCode + "</td>";
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
		
				public String updatePayment(String adminRemark ,String ID){
					{
						 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
							 
						 {return "Error while connecting to the database for updating."; }
						 
						 // create a prepared statement
						 
						 String query = "UPDATE payments SET adminRemark=? WHERE paymentID =?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 // binding values
						 preparedStmt.setString(1, adminRemark);
						 preparedStmt.setInt(2, Integer.parseInt(ID)); 
					
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Updated successfully";
						 }
						 catch (Exception e)
						 {
						 output = "Error while updating the item.";
						 System.err.println(e.getMessage());
						 }
						 return output; 
						
		} 

	}

				
}