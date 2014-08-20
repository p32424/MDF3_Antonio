package com.arianaantonio.readable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("Widget Provider", "Inside on update");
		Uri website = Uri.parse("http://www.google.com");
		Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
		
		Intent intent = new Intent(context, DetailActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, websiteIntent, 0);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		views.setOnClickPendingIntent(R.id.button1, pendingIntent);
		views.setTextViewText(R.id.nextBook, "Gone Girl");
		views.setTextViewText(R.id.nextBookTitle, "Your next book to read"); 
		
		
		appWidgetManager.updateAppWidget(appWidgetIds, views);  
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.i("Widget Provider", "Inside onEnabled");
		BufferedWriter writer = null;

				try {
					String filePath = context.getFilesDir().getPath().toString() + "/BookList.txt";
					File file = new File(filePath);
					System.out.println(file.getCanonicalPath());
					//file.delete();
					writer = new BufferedWriter(new FileWriter(file, true));
					String lotr = "The Lord Of The Rings\n";
					String goneGirl = "Gone Girl\n";
					writer.write(lotr);
					writer.write(goneGirl);  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}

	 
}
