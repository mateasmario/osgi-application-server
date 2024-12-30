package com.docsbymario.cache.provider;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.docsbymario.cache.Cache;

@Component
public class InMemoryCache implements Cache {
	private Map<String, String> data = new HashMap<>();

	@Override
	public String getData(String key) {
		System.out.println("Accessing InMemoryCache...");
		return data.get(key);
	}

	@Override
	public void putData(String key, String value) {
		System.out.println("Added " + key + ":" + value + " to InMemoryCache...");
		data.put(key, value);
	}

}
