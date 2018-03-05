package com.nisum.app.Assertion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nisum.app.client.SynchronousGetRequest;

public class AssertJSONResponse {
	
	public void getJSONResponseMap(){
		
	}
	
	public static void main(String [] args){
		SynchronousGetRequest getRequest = new SynchronousGetRequest();
		try {
			//getRequest.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getRequest.convertJSonResponseToFlatMap();
		Map responseMap = getRequest.getFlatResponseMap();
		
	}
	
	
	private List getAssertableElements(Map responseMap){
		List<String> assertableKeys = new ArrayList<String>();
		Set<String> keys = responseMap.keySet();
		assertableKeys.addAll(keys);
		
		Iterator<String> keyIterator = keys.iterator();
		while(keyIterator.hasNext()){
			String key = keyIterator.next();
			
		}
		return assertableKeys;
		
		
		
	}
	
	
	
	
	
}
