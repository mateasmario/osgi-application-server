package com.docsbymario.controller.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.docsbymario.cache.Cache;
import com.docsbymario.controller.Controller;
import com.docsbymario.data.request.Request;
import com.docsbymario.data.response.Response;
import com.docsbymario.repository.Repository;

@Component
public class StudentController implements Controller {
	@Reference
	private Cache cache;
	@Reference
	private Repository repository;
	
	private Map<String, Function<Request, Response>> endpoints = new HashMap<>();
	
	{
		endpoints.put("/getStudent", this::getStudent);
		endpoints.put("/addStudent", this::addStudent);
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
	
	public Response getStudent(Request request) {		
		Response response = new Response();
		String id = request.getBody().getContent();
		
		String data = cache.getData(id);
				
		if (data == null) {
			data = repository.getById(id);
			if (data != null) {
				cache.putData(id, data);
			}
		}
		
		response.setStatusCode(data == null ? 404 : 200);
		response.setBody(data);
		
		return response;
	}
	
	public Response addStudent(Request request) {
		Response response = new Response();
		
		String data = request.getBody().getContent();
		String[] dataSplit = data.split(" ");
		
		if (dataSplit.length < 2) {
			response.setStatusCode(400);
			return response;
		}
		
		if (!repository.insert(dataSplit[0], dataSplit[1])) {
			response.setStatusCode(409);
			response.setBody("Resource already exists. Cannot insert.");
		}
		else {
			response.setStatusCode(201);
		}
		
		return response;
	}
}
