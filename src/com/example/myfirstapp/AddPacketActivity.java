package com.example.myfirstapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.myfirstapp.MultipleChoiceActivity.MyCustomAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class AddPacketActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.message";
	
	EditText inputData;
	String[] values = new String[] {};
	String[] links;
	int[] exCounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_packet);
        setListAdapter(new ArrayAdapter(this, R.layout.packet_list, values));
    }

    public void search(View view) throws InterruptedException, ExecutionException{
    	EditText editText = (EditText) findViewById(R.id.search_terms);
    	String terms = editText.getText().toString();
    	SendDataActivity sendData = new SendDataActivity();
    	String get = sendData.startTransferLogin(terms);
    	Log.i("response",get);
    	String[] temp = get.split("!@%@");
    	values = new String[temp.length];
    	links = new String[temp.length];
    	exCounts = new int[temp.length];
    	for(int i=0; i<temp.length; i++){
    		String[] temp2 = temp[i].split("!f@!");
    		values[i] = temp2[0];
    		links[i] = temp2[1];
    		exCounts[i] = Integer.parseInt(temp2[2]);
    	}
    	setListAdapter(new ArrayAdapter(this, R.layout.packet_list, values));
    	
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
  	  DownloadPacket downloadPacket = new DownloadPacket();
  	  DatabaseHandler dbHandler = new DatabaseHandler(this);
  	  try {
		downloadPacket.getPacketByURL(links[position], values[position],dbHandler, exCounts[position]);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	}
}
