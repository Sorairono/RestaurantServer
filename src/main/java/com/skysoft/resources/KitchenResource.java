package com.skysoft.resources;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.skysoft.model.KitchenProductModel;
import com.skysoft.response.ResponseModel;
import com.skysoft.service.KitchenService;

@Path("Kitchen")
public class KitchenResource {

	@POST
	@Path("SendOrderToKitchen")
	@Produces("application/json")
	public Response sendOrderToKitchen(List<KitchenProductModel> model) {
		ResponseModel response = null;
		boolean check = KitchenService.getInstance().sendOrderToKitchen(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}
}
