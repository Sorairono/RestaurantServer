package com.skysoft.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.skysoft.model.ProductModel;
import com.skysoft.request.FindProductByType;
import com.skysoft.service.ProductService;

@Path("Product")
public class ProductResource {

	@POST
	@Path("GetProductsList")
	@Produces("application/json")
	public Response getProductsList() {
		List<ProductModel> productsList = new ArrayList<ProductModel>();
		try {
			productsList = ProductService.getInstance().getProductsList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(productsList).build();
	}
	
	@POST
	@Path("GetProductsListByType")
	@Produces("application/json")
	public Response getProductsListByType(FindProductByType model) {
		List<ProductModel> productsListByType = new ArrayList<ProductModel>();
		try {
			productsListByType = ProductService.getInstance().getProductsListByType(model);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(productsListByType).build();
	}
	
//	@POST
//	@Path("AddProduct")
//	@Produces("application/json")
//	public Response addProduct() {
//		ResponseModel response = null;
//		boolean check = ProductService.getInstance().addProduct();
//		if (check) {
//			response = new ResponseModel(200, "Success", true);
//		} else {
//			response = new ResponseModel(404, "Failure", false);
//		}
//		return Response.ok(response).build();
//	}
	
//	@POST
//	@Path("InitializeProducts")
//	@Produces("application/json")
//	public void initializeProducts() {
//		ProductService.getInstance().initializeProducts();
//	}
}
