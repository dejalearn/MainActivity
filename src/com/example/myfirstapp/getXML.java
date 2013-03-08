package com.example.myfirstapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class getXML{
    
    
    //Begins transfer of data, sets fields to parameter inputFields
public HashMap<String,String> startTransfer(String exNum, String packet) throws InterruptedException, ExecutionException, IOException{
    	new dataTransfer().execute(exNum).get();
    	HashMap<String, String> info = new HashMap<String, String>();
    	File sdcard = Environment.getExternalStorageDirectory();
    	File file = new File(sdcard,"/"+packet+".xml");
    	InputStream is = new BufferedInputStream(new FileInputStream(file));
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = null;
    	Document dom = null;
		try {
			db = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			dom = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	Element docEle = dom.getDocumentElement();
    	NodeList nl = docEle.getElementsByTagName("exercise");
    	int index = Integer.parseInt(exNum);
    	Node exercise = nl.item(index);
    	String type = exercise.getChildNodes().item(3).getFirstChild().getNodeValue();
    	String question = exercise.getChildNodes().item(5).getFirstChild().getNodeValue();
    	//question = question.replace("\\", "");
    	String answer = exercise.getChildNodes().item(7).getFirstChild().getNodeValue();
    	String hint = exercise.getChildNodes().item(9).getFirstChild().getNodeValue();
    	
    	if(type.equals("MC")){
    		NodeList choices = exercise.getChildNodes().item(11).getChildNodes();
    		String choiceA = choices.item(1).getFirstChild().getNodeValue();
    		String choiceB = choices.item(3).getFirstChild().getNodeValue();
    		String choiceC = choices.item(5).getFirstChild().getNodeValue();
    		String choiceD = choices.item(7).getFirstChild().getNodeValue();
    		Log.i("count",choiceB);
    		
    		info.put("type", type);
    		info.put("exID", exNum);
    		info.put("question",question);
    		info.put("answer", answer);
    		info.put("hint", hint);
    		info.put("choiceA", choiceA);
    		info.put("choiceB", choiceB);
    		info.put("choiceC", choiceC);
    		info.put("choiceD", choiceD);

    	}
    	
    	if(type.equals("imgMC")){
    		String pictureLink = exercise.getChildNodes().item(11).getFirstChild().getNodeValue();
    		NodeList choices = exercise.getChildNodes().item(13).getChildNodes();
    		String choiceA = choices.item(1).getFirstChild().getNodeValue();
    		String choiceB = choices.item(3).getFirstChild().getNodeValue();
    		String choiceC = choices.item(5).getFirstChild().getNodeValue();
    		String choiceD = choices.item(7).getFirstChild().getNodeValue();
    		Log.i("count",pictureLink);
    		
    		info.put("type", type);
    		info.put("exID", exNum);
    		info.put("question",question);
    		info.put("answer", answer);
    		info.put("hint", hint);
    		info.put("choiceA", choiceA);
    		info.put("choiceB", choiceB);
    		info.put("choiceC", choiceC);
    		info.put("choiceD", choiceD);
    		info.put("url", pictureLink);

    	}
    	
       if(type.equals("imgFIB")){
    	   String pictureLink = exercise.getChildNodes().item(11).getFirstChild().getNodeValue();
    	   info.put("url", pictureLink);
    	   info.put("type", type);
   		   info.put("exID", exNum);
   		   info.put("question",question);
   		   info.put("answer", answer);
   		   info.put("hint", hint);
       }
    	
       Iterator iter = info.keySet().iterator();
       while(iter.hasNext()) {
    	   //Integer key = (Integer)iter.next();
    	   String key = (String)iter.next();
    	   String val = (String)info.get(key);
    	   val = val.replace("\\", "");
    	   info.put(key,val);
       }
        
    	return info;
    }
    

    
 private class dataTransfer extends AsyncTask<String, Void, Void>{
        
        protected Void doInBackground(String...data){
            URL url = null;
			try {
				url = new URL("http://www.dejalearn.com/exercises/newhost.xml");
			} catch (MalformedURLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
        	HttpURLConnection urlConnection = null;
			
        	try {
				urlConnection = (HttpURLConnection) url.openConnection();
			} catch (IOException e6) {
				// TODO Auto-generated catch block
				e6.printStackTrace();
			}

        	try {
				urlConnection.setRequestMethod("GET");
			} catch (ProtocolException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}

        	urlConnection.setDoOutput(true);

        	try {
				urlConnection.connect();
			} catch (IOException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}

        	File SDCardRoot = Environment.getExternalStorageDirectory();

        	File file = new File(SDCardRoot,"newhost.xml");

        	FileOutputStream fileOutput = null;
			try {
				fileOutput = new FileOutputStream(file);

			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

        	InputStream inputStream = null;
			try {
				inputStream = urlConnection.getInputStream();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

        	int totalSize = urlConnection.getContentLength();

        	int downloadedSize = 0;

        	byte[] buffer = new byte[1024];

        	int bufferLength = 0; 


        	try {
				while ( (bufferLength = inputStream.read(buffer)) > 0 ) 

				{
					
					try {
						fileOutput.write(buffer, 0, bufferLength);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		
					downloadedSize += bufferLength;

					int progress=(int)(downloadedSize*100/totalSize);

				}
			
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        	try {
				fileOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return null;
        }
    }
}