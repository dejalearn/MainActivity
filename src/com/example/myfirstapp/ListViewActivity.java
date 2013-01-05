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

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {
    /** Called when the activity is first created. */
	private TextView selection;
	private TextView result;
	private TextView questionView;
	String str="";
	int correctAnswer = 0;
	int userAnswer;
	private String hint;
	private String[] items = {"a","b","c","d"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent= getIntent(); 
        String question = myIntent.getStringExtra("question"); 
        String choice0 = myIntent.getStringExtra("choice0");
        String choice1 = myIntent.getStringExtra("choice1");
        String choice2 = myIntent.getStringExtra("choice2");
        String choice3 = myIntent.getStringExtra("choice3");
        hint = myIntent.getStringExtra("hint");
        items[0] = choice0;
        items[1] = choice1;
        items[2] = choice2;
        items[3] = choice3;
        correctAnswer = Integer.parseInt(myIntent.getStringExtra("correct"));
        setContentView(R.layout.multichoice_activity);
        setListAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,items));
        questionView=(TextView)findViewById(R.id.question);
        questionView.setText(question);
    }
   
    
    public void onListItemClick(ListView parent,View v,int position,long id){
    	userAnswer = position;
    }
    
    
    public void checkAnswer(View v){
    	result=(TextView)findViewById(R.id.answer);
    	if(userAnswer == correctAnswer){
    		result.setText("Correct!");
    	}
    	else{
    		new AlertDialog.Builder(this).setTitle("Oops. Here's a hint.").setMessage(hint).setNeutralButton("Close", null).show();  

    	}
    }
}