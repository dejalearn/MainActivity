package com.example.myfirstapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 
public class ImgView extends ListActivity {
	private TextView questionView;
	private int userAnswer;
	private int correctAnswer = 0;
	private String[] items = {"a","b","c","d"};
	private String hint;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent= getIntent(); 
        HashMap<String, String> info = (HashMap<String, String>)intent.getSerializableExtra("info");
		String question = info.get("question");
		String choice0 = info.get("choiceA");
		String choice1 = info.get("choiceB");
		String choice2 = info.get("choiceC");
		String choice3 = info.get("choiceD");
		hint = info.get("hint");
		items[0] = choice0;
		items[1] = choice1;
		items[2] = choice2;
		items[3] = choice3;
		correctAnswer = Integer.parseInt(info.get("answer"));
		String url = info.get("url");

        setContentView(R.layout.imageview);
        questionView=(TextView)findViewById(R.id.question);
        questionView.setText(question);
        WebView myWebView = (WebView) findViewById(R.id.img);                   
        myWebView.loadUrl(url);
        setListAdapter(new ArrayAdapter(this,R.layout.custom_list_item,items));

    }
    
    public void onListItemClick(ListView parent,View v,int position,long id){
    	userAnswer = position+1;
    }
    
    
    public void checkAnswer(View v){
    	
    	if(userAnswer == correctAnswer){
    		new AlertDialog.Builder(this).setTitle("Correct!").setMessage("").setNeutralButton("Close", null).show();  
    	}
    	else{
    		new AlertDialog.Builder(this).setTitle("Oops. Here's a hint.").setMessage(hint).setNeutralButton("Close", null).show();  

    	}
    }
   
}