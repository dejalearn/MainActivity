package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.message";
	
	EditText inputData;
	String url = "http://www.server34.000webhost.com/testFIle.php";
	
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
    
    public void goToMC(View view){
    	Intent intent = new Intent(this, ListViewActivity.class);
    	intent.putExtra("question","What is the definition of \"abrogate\"?");
    	intent.putExtra("choice0","To revoke, formally");
    	intent.putExtra("choice1","To anger or upset");
    	intent.putExtra("choice2","To change drastically");
    	intent.putExtra("choice3","To bridge together, combine");
    	intent.putExtra("hint", "Example Sentence: \"Executions also abrogate  the possibility of redemption.\"");
    	intent.putExtra("correct","0");
    	startActivity(intent);
    }
    
}
