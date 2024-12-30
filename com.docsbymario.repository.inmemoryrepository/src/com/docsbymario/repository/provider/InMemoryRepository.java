package com.docsbymario.repository.provider;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.docsbymario.repository.Repository;

@Component
public class InMemoryRepository implements Repository {
	private Map<String, String> items = new HashMap<>();
	
	{
		items.put("LM00001", "Student 1");
		items.put("LM00002", "Student 2");
		items.put("LM00003", "Student 3");
		items.put("LM00004", "Student 4");
		items.put("LM00005", "Student 5");
	}

	@Override
	public String getById(String id) {
		return items.get(id);
	}

	@Override
	public boolean insert(String id, String value) {
		if (items.get(id) != null) {
			return false;
		}
		else {
			items.put(id, value);
			return true;
		}
	}

}
