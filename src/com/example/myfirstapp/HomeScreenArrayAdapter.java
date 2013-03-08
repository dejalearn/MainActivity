package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeScreenArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  int [] progVals;
  int screenWidth;

  public HomeScreenArrayAdapter(Context context, String[] values, int[] progs, int screenWidth) {
    super(context, R.layout.view_rox, values);
    this.context = context;
    this.values = values;
    progVals = progs;
    this.screenWidth = screenWidth;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    
    View rowView = inflater.inflate(R.layout.view_rox, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.tvViewRow);
    textView.setWidth(screenWidth);
    Button quiz = (Button) rowView.findViewById(R.id.BtnToClick);
    quiz.setTag(values[position]);
    ProgressBar progress = (ProgressBar) rowView.findViewById(R.id.progBar);
    TextView pct = (TextView) rowView.findViewById(R.id.percent);
    pct.setTypeface(null,Typeface.BOLD);
    String numberPct = Integer.toString(progVals[position])+"%";
    if(numberPct.equals("0%")){
    	numberPct = "  0%";
    }
    pct.setText(numberPct);
    progress.setProgress(progVals[position]);
    textView.setText(values[position]);
    

    return rowView;
  }
} 