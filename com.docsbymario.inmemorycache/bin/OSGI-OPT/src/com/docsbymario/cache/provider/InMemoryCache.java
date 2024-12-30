package com.docsbymario.cache.provider;

import java.util.Map;

import com.docsbymario.cache.Cache;

public class InMemoryCache implements Cache {
	private Map<String, String> data;

	@Override
	public String getData(String key) {
		return data.get(key);
	}

	@Override
	public void putData(String key, String value) {
		data.put(key, value);
	}

}
