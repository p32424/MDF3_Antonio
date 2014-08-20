package com.arianaantonio.readable;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;
 

public class WidgetConfigure extends Activity {
	
	ArrayList<String> arrayForSpinner = new ArrayList<String>();
	Context context;  
	String colorPicked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BufferedWriter writer = null;
		
		//String filePath = context.getFilesDir().getPath().toString() + "/BookList.txt";
		//File file = new File(filePath);
		//try {
			//System.out.println(file.getCanonicalPath());
			//file.delete();
			//writer = new BufferedWriter(new FileWriter(file, true));
			//String lotr = "The Lord Of The Rings";
			//String goneGirl = "Gone Girl";
			//writer.write(lotr);
			//writer.write(goneGirl);  
		//} //catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		context = this;
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		arrayForSpinner.add("Red");
		arrayForSpinner.add("Green");
		arrayForSpinner.add("Purple");
		arrayForSpinner.add("Blue");
		arrayForSpinner.add("Orange");
		arrayForSpinner.add("Yellow");
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayForSpinner);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				colorPicked = arrayForSpinner.get(position);
				Log.i("Main Activity", "Color Selected: " +colorPicked);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Bundle extras = getIntent().getExtras();
				
				 if (extras != null) {
					 int widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
				
					 if (widgetID != AppWidgetManager.INVALID_APPWIDGET_ID) {
						
						 EditText userNameField = (EditText) findViewById(R.id.editText1);
						 String userName = userNameField.getText().toString();
						 Log.i("Main Activity", "Username: " +userName);
						
						 
						 AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
						 
						 WidgetProvider widgetActivity = new WidgetProvider();
						 //widgetActivity.Save = 
						 
						 RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
						 appWidgetManager.updateAppWidget(widgetID, view);
						 view.setTextViewText(R.id.nextBook, "The Lord Of The Rings");
						 view.setTextViewText(R.id.nextBookTitle, "Next book to read for " +userName); 
						 
						 Uri website = Uri.parse("http://www.google.com");
						 Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
						  
						 PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, websiteIntent, 0);
						 view.setOnClickPendingIntent(R.id.button1, pendingIntent);
						 Intent result = new Intent();
						 result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
						 setResult(RESULT_OK, result);
						 finish();  
						 
					 }
					 
				 }
				
			}
			
		});
	}
	
	

}
