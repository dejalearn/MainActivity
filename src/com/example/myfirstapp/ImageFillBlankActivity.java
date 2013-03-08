package com.example.myfirstapp;


import java.util.HashMap;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
 
public class ImageFillBlankActivity extends Activity {
	private TextView questionView;
	private String userAnswer;
	private String correctAnswer;
	private String hint;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent(); 
        HashMap<String, String> info = (HashMap<String, String>)intent.getSerializableExtra("info");
        String question = info.get("question");
        String url = info.get("url");
        hint = info.get("hint");
        correctAnswer = info.get("answer");
        setContentView(R.layout.image_fill_in_blank);
        questionView=(TextView)findViewById(R.id.question);
        questionView.setText(question);
        WebView myWebView = (WebView) findViewById(R.id.img);                   
        myWebView.loadUrl(url);
        

    }
    

    
    
    public void checkAnswer(View v){
    	
    	EditText editText = (EditText) findViewById(R.id.userAnswer);
    	userAnswer = editText.getText().toString();
    	Log.i("User input", userAnswer);
    	if(userAnswer.equals(correctAnswer)){
    		new AlertDialog.Builder(this).setTitle("Correct!").setMessage("").setNeutralButton("Close", null).show();  
    	}
    	else{
    		new AlertDialog.Builder(this).setTitle("Oops. Here's a hint.").setMessage(hint).setNeutralButton("Close", null).show();  

    	}
    }
   
}