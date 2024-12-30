package com.docsbymario.cache.provider;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.docsbymario.cache.Cache;

@Component
public class NoCache implements Cache {
	@Override
	public String getData(String key) {
		System.out.println("Accessing NoCache...");
		return null;
	}

	@Override
	public void putData(String key, String value) {
		System.out.println("Trying to add data to NoCache...");
		// Do nothing, as it is a NoCache.
	}

}
