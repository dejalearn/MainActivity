package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter {
    Typeface typeface;
	public MyArrayAdapter(Context context, int textViewResourceId, Typeface typeFace) {
		super(context, textViewResourceId);
		this.typeface=typeFace;
		// TODO Auto-generated constructor stub
	}
	
	@Override  
	public View getView(int position, View view, ViewGroup viewGroup)
	{
	 
	 View v = super.getView(position, view, viewGroup);
	 ((TextView)v).setTypeface(typeface);
	 return v;
	}

}
