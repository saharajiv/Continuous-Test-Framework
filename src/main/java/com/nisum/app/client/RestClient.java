package com.nisum.app.client;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClient {
  public static final String mediaType =  "application/json;charset=utf-8";
  public static final MediaType JSON= MediaType.parse(mediaType);
  public static final String URL = "http://l4688338:8180/api/order/v1/bags/";//client-host+Resource-Path
  
  //List<Pair<String, String>> pairs = new ArrayList<>();
  
  OkHttpClient client = new OkHttpClient();

  String postWithoutRequestParams(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  
  String postWithRequestParams(String url,String json, List<String> params ) throws IOException{
	  
	  // HashMap with Params
	  Map<String, String> requestParams = new HashMap<>();
	  requestParams.put( "Param1", "A" );
	  requestParams.put( "Param2", "B" );
	  // Initialize Builder (not RequestBody)
	  
	  FormBody.Builder builder = new FormBody.Builder();
	  
	  
	  // Add Params to Builder
	  for ( Map.Entry<String, String> entry : requestParams.entrySet() ) {
	      builder.add( entry.getKey(), entry.getValue() );
	  }
	  
	  //RequestBody body = RequestBody.create(JSON, json);
	  
	  RequestBody formBody = builder.build();
	  RequestBody requestBody = formBody.create(JSON, json);
	  
	  Request request = new Request.Builder()
	        .url(url)
	        .post(requestBody)
	        .build();
	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    } 
  }
  
  
/*  public String postToServer(String url, List<Pair<String, String>> pairs) throws Exception {
	    okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
	    okhttp3.Request.Builder builder = new okhttp3.Request.Builder().url(url);

	    if (pairs != null) {
	        okhttp3.FormBody.Builder postData = new okhttp3.FormBody.Builder();
	        for (Pair<String, String> pair : pairs) {
	            postData.add(pair.first, pair.second);
	        }
	        builder.post(postData.build());
	    }
	    okhttp3.Request request = builder.build();
	    okhttp3.Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException(response.message() + " " + response.toString());
	    }
	    return response.body().string();
	}*/
  

  String bowlingJson(String player1, String player2) {
    return "{'winCondition':'HIGH_SCORE',"
        + "'name':'Bowling',"
        + "'round':4,"
        + "'lastSaved':1367702411696,"
        + "'dateStarted':1367702378785,"
        + "'players':["
        + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
        + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
        + "]}";
  }

  public static void main(String[] args) throws IOException {
	String URL = "";
	RestClient example = new RestClient();
    String json = example.bowlingJson("Jesse", "Jake");
    String response = example.postWithoutRequestParams(URL, json);//("http://www.roundsapp.com/post", json);
    
    System.out.println(response);
  }
}