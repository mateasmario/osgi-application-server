package com.docsbymario.blacklist;

import com.docsbymario.data.request.Request;

public interface Blacklist {
	boolean disallow(Request request);
}
