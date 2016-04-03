package com.telstra.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkProcess {
	
	public ArrayList<String> HTTP_Request(){
		ArrayList<String> array = new ArrayList<String>();
		String url = "https://dl.dropboxusercontent.com/u/746330/facts.json";
		HttpParams httpparam = new BasicHttpParams();
		HttpClient client = new DefaultHttpClient(httpparam);
		StringBuilder sbuild = new StringBuilder();
		HttpGet httpget = new HttpGet(url);
		try{
		HttpResponse response = client.execute(httpget);
		StatusLine statusline = response.getStatusLine();
		int scode = statusline.getStatusCode();
		if(scode==200){
			HttpEntity entry = response.getEntity();
			InputStream content = entry.getContent();
			BufferedReader bf = new BufferedReader(new InputStreamReader(content));
			String lines = "";
			while((lines= bf.readLine())!=null){
				sbuild.append(lines);
			}
		}
		array = JSON_Parse(sbuild+"");
		}catch(ClientProtocolException cpe){
			cpe.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public ArrayList<String> JSON_Parse(String Responce){
		String Response = Responce;
		ArrayList<String> as = new ArrayList<String>();
		try{
			JSONObject jsonObject = new JSONObject(Response);
			String tString = jsonObject.getString("title");
			as.add(tString);
			String Response1 = jsonObject.getString("rows");
			JSONArray jsonArray = new JSONArray(Response1);
			for(int i=0; i< jsonArray.length(); i++){
				JSONObject jobject = jsonArray.getJSONObject(i);
				if(jobject.getString("title")!="null"){
					as.add(jobject.getString("title"));}
				else
					as.add("");
				if(jobject.getString("description")!="null"){
					as.add(jobject.getString("description"));}
				else
					as.add("");
				if(jobject.getString("imageHref")!="null"){
					as.add(jobject.getString("imageHref"));}
				else
					as.add("");
			}
		}catch(JSONException je){
			je.printStackTrace();
		}
		return as;
	}
}
