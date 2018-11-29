package com.connected;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.widget.Toast;

public class HttpClientThread extends Thread {
	private String url;
	private String account;
	private String password;
	private HttpResponse response;
	private Context context;
	public HttpClientThread(String url, String account, String password,Context context) {
		this.url = url;
		this.account = account;
		this.password = password;
		this.context=context;
	}
	
	public HttpResponse getResponse() {
		return response;
	}

	private void doHttpClientPost(){
		HttpClient client=new DefaultHttpClient();
		HttpPost post=new HttpPost(url);
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("account", account));
		list.add(new BasicNameValuePair("password", password));
		try {
			post.setEntity(new UrlEncodedFormEntity(list));
			response=client.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				System.out.println(EntityUtils.toString(response.getEntity()));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		doHttpClientPost();
	}
}
