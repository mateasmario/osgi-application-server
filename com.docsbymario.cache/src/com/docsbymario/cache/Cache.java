package com.docsbymario.cache;

public interface Cache {
	String getData(String key);
	void putData(String key, String value);
}
