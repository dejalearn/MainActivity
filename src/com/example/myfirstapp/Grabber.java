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


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Grabber extends Activity{

	View view;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SendData().execute();
    }
    
    public void getMultipleChoice(String[] tokens, View view){
		Intent intent = new Intent(this, MultipleChoiceActivity.class);
    	intent.putExtra("question",tokens[2]);
    	intent.putExtra("choice0",tokens[4]);
    	intent.putExtra("choice1",tokens[5]);
    	intent.putExtra("choice2",tokens[6]);
    	intent.putExtra("choice3",tokens[7]);
    	intent.putExtra("hint", tokens[8]);
    	intent.putExtra("correct",tokens[3]);
    	intent.putExtra("difficulty", tokens[1]);
    	startActivity(intent);
	}
	
    private class SendData extends AsyncTask<Void, Void, Void>{ 

		
		String url = "http://www.dejalearn.hostoi.com/getExercise.php";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		protected Void doInBackground(Void...params){
		// HTTP POST Request
				try {
					List<NameValuePair> nameValues = new ArrayList<NameValuePair>(1);
					nameValues.add(new BasicNameValuePair("id", "1"));
					httppost.setEntity(new UrlEncodedFormEntity(nameValues));
		
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity resEntity = response.getEntity();
					String delim = "--";
					String res = EntityUtils.toString(resEntity);
					String[] tokens = res.split(delim);
					String exerciseType = tokens[0];
					
					//if (resEntity != null) {
					//	Log.i("Response", EntityUtils.toString(resEntity));
					//}
					
					if(exerciseType.equals("MultChoice")){
						Log.i("Debug","Made it tis far");
						Grabber.this.getMultipleChoice(tokens,view);
					}
					// goToMC(view,tokens[2],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[3]);
		
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		}
	}
	
	
	
	
}
