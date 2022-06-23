package com.customer.server;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.customer.model.*;


@Path("/Customer")
public class customerService {

	
customerModel customerObj = new customerModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return customerObj.readCustomer();
	}
	
	// Insert Customer

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
			@FormParam("cusName")String cusName,
			@FormParam("cusAddress")String cusAddress,
			@FormParam("cusPhoneNo")String cusPhoneNo,
			@FormParam("cusEmail")String cusEmail,
			@FormParam("cusNIC")String cusNIC)
	{
		String output = customerObj.insertCustomer(cusName, cusAddress, cusPhoneNo, cusEmail, cusNIC );
		return output;
	}
	
	    //Update Customer
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String customerObject(String customerData) {
			
			//Convert the input string to a JSON object 
			JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
				
			String cusID = customerObject.get("cusID").getAsString();
			String cusName = customerObject.get("cusName").getAsString();
			String cusAddress = customerObject.get("cusAddress").getAsString();
			String cusPhoneNo = customerObject.get("cusPhoneNo").getAsString();
			String cusEmail = customerObject.get("cusEmail").getAsString();
			String cusNIC = customerObject.get("cusNIC").getAsString();
			
			String output = customerObj.updateCustomer(cusID, cusName, cusAddress, cusPhoneNo, cusEmail, cusNIC );
			
			return output;
		}
	
		//	Delete Customer

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteCustomer(String customerData)
		{
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
		
			//Read the value from the element <Account No>
			String cusID = doc.select("cusID").text();
			
			String output = customerObj.deleteCustomer(cusID);
			return output;
		}


}
	
	
	
	
