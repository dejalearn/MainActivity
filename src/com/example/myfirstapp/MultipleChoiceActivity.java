package com.example.myfirstapp;

import java.util.HashMap;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MultipleChoiceActivity extends ListActivity {
	
	public class MyCustomAdapter extends ArrayAdapter<String> {

		Typeface typeFace; 
		public MyCustomAdapter(Context context, int textViewResourceId,
		String[] objects,Typeface typeface) 
		{
				super(context, textViewResourceId, objects);
				this.typeFace = typeface;
				
				// TODO Auto-generated constructor stub
		}
		
		@Override  
		public View getView(int position, View view, ViewGroup viewGroup)
		{
		 View v = super.getView(position, view, viewGroup);
		 ((TextView)v).setTypeface(typeFace);
		 
		 if(position==0){
			 ((TextView)v).setTextColor(Color.parseColor("#f010fe"));
		 }
		 
		 
		 if(position==1){
			 ((TextView)v).setTextColor(Color.parseColor("#03e7fd"));
		 }
		 
		 if(position==2){
			 ((TextView)v).setTextColor(Color.parseColor("#eefc21"));
		 }
		 
		 if(position==3){
			 ((TextView)v).setTextColor(Color.parseColor("#04eb02"));
		 }
		 return v;
		}


		
	}

	
	
	/** Called when the activity is first created. */
	private TextView questionView;
	int correctAnswer = 0;
	int userAnswer;
	private String hint;
	private String[] items = { "a", "b", "c", "d" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		//String get = myIntent.getStringExtra("message");
		//String tokens[] = get.split("--");
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
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Schalk.ttf");
		Log.i("typeface","HEEEEEEREE");
		setListAdapter(new MyCustomAdapter(this, R.layout.custom_list_item, items, typeFace));
		setContentView(R.layout.multichoice_activity);
		//setListAdapter(new ArrayAdapter(this, R.layout.custom_list_item, items));
		
		
		questionView = (TextView) findViewById(R.id.question);
		
		questionView.setText(question);
		questionView.setTypeface(typeFace);
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		userAnswer = position + 1;
	}

	public void checkAnswer(View v) {

		if (userAnswer == correctAnswer) {
			new AlertDialog.Builder(this).setTitle("Correct!").setMessage("")
					.setNeutralButton("Close", null).show();
		} else {
			new AlertDialog.Builder(this).setTitle("Oops. Here's a hint.")
					.setMessage(hint).setNeutralButton("Close", null).show();

		}
	}
}