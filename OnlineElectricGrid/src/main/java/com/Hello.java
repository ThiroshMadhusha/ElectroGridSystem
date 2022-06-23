package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.PaymentHistory;

@Path("/paymentHistory") 
public class Hello {


	 PaymentHistory paymentObj = new PaymentHistory();
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentHistory()
	
	
	
	{
		return paymentObj.readPaymentHistory();
	}
	

}
