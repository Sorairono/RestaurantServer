package com.skysoft.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.skysoft.model.*;
import com.skysoft.request.AddToTableCart;
import com.skysoft.request.ChangeAmountInCart;
import com.skysoft.request.DeleteProductFromCart;
import com.skysoft.request.SendOrderTableModel;
import com.skysoft.request.TableNumberModel;
import com.skysoft.response.ResponseModel;
import com.skysoft.service.TableService;

@Path("Table")
public class TableResource {

	@POST
	@Path("GetTablesList")
	@Produces("application/json")
	public Response getTablesList() {
		List<TableModel> tablesList = new ArrayList<TableModel>();
		try {
			tablesList = TableService.getInstance().getTablesList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(tablesList).build();
	}

	@POST
	@Path("GetTablesListInZone")
	@Produces("application/json")
	public Response getTablesListInZone(ZoneModel zone) {
		List<TableModel> tablesListInZone = new ArrayList<TableModel>();
		try {
			tablesListInZone = TableService.getInstance().getTablesListInZone(zone);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(tablesListInZone).build();
	}

	@POST
	@Path("GetTable")
	@Produces("application/json")
	public Response getTable(TableNumberModel model) {
		TableModel table = null;
		try {
			table = TableService.getInstance().getTable(model);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.ok(table).build();
	}

	@POST
	@Path("OpenTable")
	@Produces("application/json")
	public Response openTable(TableNumberModel model) {
		ResponseModel response = null;
		boolean check = TableService.getInstance().openTable(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("AddToTableCart")
	@Produces("application/json")
	public Response addToTableCart(AddToTableCart model) {
		ResponseModel response = null;
		boolean check = TableService.getInstance().addToTableCart(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("ChangeAmountInCart")
	@Produces("application/json")
	public Response changeAmountInCart(ChangeAmountInCart model) {
		ResponseModel response = null;
		boolean check = TableService.getInstance().changeAmountInCart(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("DeleteProductFromCart")
	@Produces("application/json")
	public Response deleteProductFromCart(DeleteProductFromCart model) {
		ResponseModel response = null;
		boolean check = TableService.getInstance().deleteProductFromCart(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("SendOrder")
	@Produces("application/json")
	public Response sendOrder(SendOrderTableModel model) {
		ResponseModel response = null;
		boolean check = TableService.getInstance().sendOrder(model);
		if (check) {
			response = new ResponseModel(200, "Success", true);
		} else {
			response = new ResponseModel(404, "Failure", false);
		}
		return Response.ok(response).build();
	}

//	@POST
//	@Path("AddTable")
//	@Produces("application/json")
//	public Response addTable() {
//		ResponseModel response = null;
//		boolean check = TableService.getInstance().addTable();
//		if (check) {
//			response = new ResponseModel(200, "Success", true);
//		} else {
//			response = new ResponseModel(404, "Failure", false);
//		}
//		return Response.ok(response).build();
//	}
}
