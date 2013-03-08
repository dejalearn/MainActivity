package com.example.myfirstapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.message";
	
	EditText inputData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void sendMessage(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void goToMC(View view) throws IOException, InterruptedException, ExecutionException{
    	//Intent intent = new Intent(this, Grabber.class);
    	//startActivity(intent);
    	/*getXML sendData = new getXML();
    	HashMap<String, String> info = sendData.startTransfer("0","newhost");
    	String type = info.get("type");
    	if(type.equals("MC")){
    		Intent intent = new Intent(this,MultipleChoiceActivity.class);
    		intent.putExtra("info", info);
    		startActivity(intent);
    	}
    	if(type.equals("imgMC")){
    		Intent intent = new Intent(this, ImgView.class);
    		intent.putExtra("info", info);
    		startActivity(intent);
    	}
    	if(type.equals("imgFIB")){
    		Intent intent = new Intent(this, ImageFillBlankActivity.class);
    		intent.putExtra("info", info);
    		startActivity(intent);
    	}*/
    	
    	DatabaseHandler db = new DatabaseHandler(this);
    	 
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        //Log.d("Insert: ", "Inserting ..");
        //Exercise ex = new Exercise("cs174b",3,"mc");
        //db.addExercise(ex);
        
        //Exercise t = db.getExercise("cs174b", 2);
        //Log.i("returned", t.packet);
    	db.updateExercise("cs174b", 3, true);
        /*
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        }*/
    	
    	

    	

    }
    
    public void goToPic(View view) throws InterruptedException, ExecutionException{
    	/*SendDataActivity sendData = new SendDataActivity();
    	String get = sendData.startTransferLogin("1");
    	Log.i("response", get);
    	String delim = "--";
		String[] tokens = get.split(delim);
		String exerciseType = tokens[0];
		//String exerciseType = "MultChoice";
		//String get = "MultChoice--3--What is the def abrogate?--3--a--b--c--d--hint";
		if(exerciseType.equals("MultChoice")){
			Log.i("Debug","Made it tis far");
			Intent intent = new Intent(this, MultipleChoiceActivity.class);
	    	intent.putExtra("message",get);
	    	startActivity(intent);
		}
		
		if(exerciseType.equals("PicMultChoice")){
			Intent intent = new Intent(this, ImgView.class);
	    	intent.putExtra("question",tokens[2]);
	    	intent.putExtra("choice0",tokens[4]);
	    	intent.putExtra("choice1",tokens[5]);
	    	intent.putExtra("choice2",tokens[6]);
	    	intent.putExtra("choice3",tokens[7]);
	    	intent.putExtra("hint", tokens[8]);
	    	intent.putExtra("correct",tokens[3]);
	    	intent.putExtra("url", tokens[9]);
	    	startActivity(intent);
		}
		
		if(exerciseType.equals("PicFillInBlank")){
	    	Intent intent = new Intent(this, ImageFillBlankActivity.class);
	    	intent.putExtra("question",tokens[2]);
	    	intent.putExtra("hint", tokens[8]);
	    	intent.putExtra("url", tokens[9]);
	    	intent.putExtra("correct", tokens[10]);
	    	startActivity(intent); 
		}*/
    	Intent intent = new Intent(this,HomeScreenActivity.class);
		startActivity(intent);
    	
    }
    
   
    
}
