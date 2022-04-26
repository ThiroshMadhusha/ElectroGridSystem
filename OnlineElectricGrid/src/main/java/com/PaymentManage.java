package com;

    import model.Payment;

  
    import javax.ws.rs.Consumes;
    import javax.ws.rs.FormParam;
    import javax.ws.rs.GET;
    import javax.ws.rs.POST;
    import javax.ws.rs.PUT;
    import javax.ws.rs.Path;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.MediaType;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

   




	
	@Path("/Payment") 

public class PaymentManage {
		
	
		
		Payment paymentObj = new Payment();
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML)
		public String readPayment()
		
		
		
		{
			return paymentObj.readPayment();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertPayment
		(@FormParam("paymentCode") String paymentCode,
		 @FormParam("paymentName") String paymentName,
		 @FormParam("paymentAmount") String paymentAmount,
		 @FormParam("paymentContact") String paymentContact)
		{
		 String output = paymentObj.insertPayment(paymentCode, paymentName, paymentAmount, paymentContact);
		return output;
		}

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
	
		 String adminRemark = paymentObject.get("adminRemark").getAsString();
		 String paymentID = paymentObject.get("paymentID").getAsString(); 
		
		 String output = paymentObj.updatePayment(adminRemark, paymentID);
			return output;
		}
		
		
	}
		
		
		
		

