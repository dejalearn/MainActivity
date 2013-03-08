package com.example.myfirstapp;

import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class HomeScreenActivity extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        "Blackberry"};
    int[] progressVals = new int[] {0,50,75,46};
    DisplayMetrics displaymetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    int height = displaymetrics.heightPixels;
    int wwidth = displaymetrics.widthPixels;
    HomeScreenArrayAdapter adapter = new HomeScreenArrayAdapter(this, values, progressVals,wwidth);
    setListAdapter(adapter);
  }
  
  public void alert(View v){
	  String tag = (String) v.getTag();
	  new AlertDialog.Builder(this).setTitle(tag).setMessage("")
		.setNeutralButton("Close", null).show();
  }
  
  public void func(View V){
	 
	  new AlertDialog.Builder(this).setTitle("works").setMessage("")
		.setNeutralButton("Closes", null).show();
  }
  
  public void onListItemClick(ListView parent, View v, int position, long id) {
	  new AlertDialog.Builder(this).setTitle("Correct!").setMessage("")
		.setNeutralButton("Close", null).show();
	}
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      // Handle item selection
      switch (item.getItemId()) {
          case R.id.randomEx:
        	Intent intent = new Intent(this, AddPacketActivity.class);
        	startActivity(intent);
            break;
          
          case R.id.help:
        	  break;
          default:
              return super.onOptionsItemSelected(item);
          
      }
      
      return true;
  }
  
  public void goToAddPacket(View view) throws InterruptedException, ExecutionException{
  	Intent intent = new Intent(this, AddPacketActivity.class);
	startActivity(intent);
  	
  }

} 