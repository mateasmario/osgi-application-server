package com.docsbymario.blacklist.provider;

import org.osgi.service.component.annotations.Component;

import com.docsbymario.blacklist.Blacklist;
import com.docsbymario.data.request.Request;

@Component
public class IPBlacklist implements Blacklist {
	@Override
	public boolean disallow(Request requestInfo) {
		return !requestInfo.getIpAddress().equals("192.168.0.1");
	}

}
