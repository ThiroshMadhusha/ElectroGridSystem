package com.bill.server;


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

import com.bill.model.*;


@Path("/Bills")

public class billService {
	
	billModel billObj = new billModel();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		return billObj.readBill();
	}
	
	// Insert Bill

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBill(
				@FormParam("billAccountNo")String billAccountNo,
				@FormParam("billName")String billName,
				@FormParam("billAddress")String billAddress,
				@FormParam("billNo")String billNo,
				@FormParam("billUnit")String billUnit,
				@FormParam("billtAmount")String billtAmount)
		{
			String output = billObj.insertBill(billAccountNo, billName, billAddress, billNo, billUnit, billtAmount );
			return output;
		}
		
		//Update Bill
		
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateBill(String billData) {
				
				//Convert the input string to a JSON object 
				JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
					
				String billID = billObject.get("billID").getAsString();
				String billAccountNo = billObject.get("billAccountNo").getAsString();
				String billName = billObject.get("billName").getAsString();
				String billAddress = billObject.get("billAddress").getAsString();
				String billNo = billObject.get("billNo").getAsString();
				String billUnit = billObject.get("billUnit").getAsString();
				String billtAmount = billObject.get("billtAmount").getAsString();
				
				String output = billObj.updateBill(billID, billAccountNo, billName, billAddress, billNo, billUnit, billtAmount);
				
				return output;
			}
			
//			Delete Bill

			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deletebill(String billData)
			{
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
			
				//Read the value from the element <Account No>
				String billID = doc.select("billID").text();
				
				String output = billObj.deletebill(billID);
				return output;
			}	
	


}
