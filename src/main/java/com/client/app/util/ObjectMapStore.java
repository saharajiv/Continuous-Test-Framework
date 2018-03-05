package com.client.app.util;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapStore {
	
	private static ObjectMapStore mapStore = new ObjectMapStore();
	
	private Map<String,Map<String,Object>> testsObjectMapper = new HashMap<String,Map<String,Object>>();
	
	//private Map<String,Object> objectMapper
	
	private ObjectMapStore(){}
	
	public static ObjectMapStore getInstance(){
		return mapStore; 
	}
	
	public void createObjectMapper(String testName){
		Map<String,Object> objectMapper = new HashMap<String,Object>();
		objectMapper.put(testName, objectMapper);
	}
	
	public void insertObject(String testName , String className,Object object){
		Map objectMapper = testsObjectMapper.get(testName);
		objectMapper.put(className, object);
	}
	
	
	public Object getObject(String testName,String className){
		Map objectMapper = testsObjectMapper.get(testName);
		Object object = objectMapper.get(className);
		return object;
	}
	
	
	
}
