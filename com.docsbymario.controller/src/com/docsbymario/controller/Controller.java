package com.docsbymario.controller;

import com.docsbymario.data.request.Request;
import com.docsbymario.data.response.Response;

public interface Controller {
	boolean canReceive(Request request);
	Response process(Request request);
}
