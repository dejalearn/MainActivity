package com.example.myfirstapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
	String url = "http://www.dejalearn.hostoi.com/testFIle.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		setContentView(textView);
		
		new SendData().execute();
	}
	
	private class SendData extends AsyncTask<Void, Void, Void>{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		
		protected Void doInBackground(Void...params){
			
			//HTTP POST Request
			try{
				List<NameValuePair> nameValues = new ArrayList<NameValuePair>(1);
				nameValues.add(new BasicNameValuePair("id", "12345"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValues));
				
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity resEntity = response.getEntity();
				
				if(resEntity != null){
					Log.i("Response", EntityUtils.toString(resEntity));
				}
			
			}catch(ClientProtocolException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;	
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}