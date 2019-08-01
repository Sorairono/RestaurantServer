package com.skysoft.resources;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.skysoft.model.ZoneModel;
import com.skysoft.service.ZoneService;

@Path("Zone")
public class ZoneResource {
	@POST
	@Path("GetZonesList")
	@Produces("application/json")
	public Response getZonesList() {
		List<ZoneModel> zonesList = ZoneService.getInstance().getZonesList();
		return Response.ok(zonesList).build();
	}
}
