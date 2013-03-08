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
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class DownloadPacket {
	String packet_url;
	String title;
	
	public String getPacketByURL(String address, String title, DatabaseHandler dbHandler, int exCount) throws IOException{
		packet_url = address;
		this.title = title;
		try {
			new dataTransfer().execute(packet_url).get();

			File sdcard = Environment.getExternalStorageDirectory();
	    	File file = new File(sdcard,"/"+title+".xml");
	    	putExercisesInDB(file,exCount,dbHandler);
	    	
	    	return "complete";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "error";
			
		} catch (ExecutionException e) {
			e.printStackTrace();
			return "error";
			
		}
		
	}
	
	private void putExercisesInDB(File file, int count, DatabaseHandler dbHandler) throws IOException{
		
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
    	for(int i=0; i<count; i++){
		    	
		    	Node exercise = nl.item(i);
		    	String type = exercise.getChildNodes().item(3).getFirstChild().getNodeValue();
		    	Exercise ex = new Exercise(title,i,type);
		        dbHandler.addExercise(ex);
		    	
    	}	
		     
        
    	
    }
		
		
	
	
	
	
	
	 private class dataTransfer extends AsyncTask<String, Void, Void>{
	        
	        protected Void doInBackground(String...data){
	            URL url = null;
				try {
					url = new URL(packet_url);
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

	        	File file = new File(SDCardRoot, title+".xml");

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
