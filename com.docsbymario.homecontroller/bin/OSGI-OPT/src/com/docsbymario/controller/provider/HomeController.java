package com.docsbymario.controller.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.osgi.service.component.annotations.Component;

import com.docsbymario.controller.Controller;
import com.docsbymario.data.request.Request;
import com.docsbymario.data.response.Response;

@Component
public class HomeController implements Controller {
	private Map<String, Function<Request, Response>> endpoints = new HashMap<>();
	
	{
		endpoints.put("/", this::getHome);
	}

	@Override
	public boolean canReceive(Request request) {
		return endpoints.get(request.getEndpoint()) != null;
	}

	@Override
	public Response process(Request request) {
		Function<Request, Response> controllerFunction = endpoints.get(request.getEndpoint());
		
		if (controllerFunction != null) {
			return endpoints.get(request.getEndpoint()).apply(request);
		}
		else {
			Response response = new Response();
			response.setStatusCode(404);
			return response;
		}
	}
	
	public Response getHome(Request request) {
		Response response = new Response();
		response.setStatusCode(200);
		response.setBody("Hello, world!");
		return response;
	}

}
