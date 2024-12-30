package com.docsbymario.inputreader.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import com.docsbymario.data.request.Authentication;
import com.docsbymario.data.request.Request;
import com.docsbymario.data.request.RequestBody;
import com.docsbymario.inputreader.InputReader;

public class StandardInputReader implements InputReader {

	@Override
	public Request read() {
		Request request = new Request();
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		try {
			System.out.println("Endpoint: ");
			request.setEndpoint(bufferedReader.readLine());
			request.setIpAddress(InetAddress.getLocalHost().toString());
			
			System.out.println("Username: ");
			request.setAuthentication(new Authentication());
			request.getAuthentication().setUsername(bufferedReader.readLine());
			
			System.out.println("Data: ");
			request.setBody(new RequestBody());
			request.getBody().setContent(bufferedReader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return request;
	}

}
