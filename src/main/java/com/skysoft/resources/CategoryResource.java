package com.skysoft.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.skysoft.model.CategoryModel;
import com.skysoft.service.CategoryService;

@Path("Category")
public class CategoryResource {

	@POST
	@Path("GetCategoriesList")
	@Produces("application/json")
	public Response getCategoriesList() {
		List<CategoryModel> categoriesList = new ArrayList<CategoryModel>();
		try {
			categoriesList = CategoryService.getInstance().getCategoriesList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(categoriesList).build();
	}
}
