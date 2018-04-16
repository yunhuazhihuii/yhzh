package com.yhzh.zhyq.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String targetURL = "http://127.0.0.1:8080/YHZHADAPTER/hello_rest/admin/123";
		try {
	   URL restServiceURL = new URL(targetURL);
	   HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
	   httpConnection.setRequestMethod("GET");
	   httpConnection.setRequestProperty("Accept", "application/json");
	   if (httpConnection.getResponseCode() != 200) {
	    throw new RuntimeException("HTTP GET Request Failed with Error code : "
	      + httpConnection.getResponseCode());
	   }
	   BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
	    (httpConnection.getInputStream())));
	   String output;
	   System.out.println("Output from Server: \n");
	   while ((output = responseBuffer.readLine()) != null) {
	    System.out.println(output);
	   }
	   httpConnection.disconnect();
	   } catch (MalformedURLException e) {
	   e.printStackTrace();
	   } catch (IOException e) {
	   e.printStackTrace();
	   }
	}

}
