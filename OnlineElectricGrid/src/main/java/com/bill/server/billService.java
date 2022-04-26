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
	


}
