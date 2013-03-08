package com.example.myfirstapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
import android.util.Log;

public class SendDataActivity{
    

    String terms;
    String returnMessage;
    //Begins transfer of data, sets fields to parameter inputFields
    public String startTransferLogin(String message) throws InterruptedException, ExecutionException{
        terms = message;
    	new dataTransfer().execute(message).get();
        return returnMessage;
    }
    

    
    
    //Actual connection with server php file. Creates name value pairs according to 
    //"fields" and input string
    private class dataTransfer extends AsyncTask<String, Void, Void>{
        
        protected Void doInBackground(String...data){
            
        	String url = "http://www.dejalearn.com/searchPacket.php";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            
            
            //HTTP POST Request
            try{
                List<NameValuePair> nameValues = new ArrayList<NameValuePair>(1);
        
                    nameValues.add(new BasicNameValuePair("id", terms));
                    
                
                httppost.setEntity(new UrlEncodedFormEntity(nameValues));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity resEntity = response.getEntity();
                
                returnMessage = EntityUtils.toString(resEntity);
               
               // go = true;
               // SendDataActivity.this.setMessage(mms);
                if(resEntity != null){
                    
                }
        
            }catch(ClientProtocolException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
    
            return null;
        }
    }
}