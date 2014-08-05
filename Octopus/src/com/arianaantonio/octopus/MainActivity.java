package com.arianaantonio.octopus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	
	WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        webview = new WebView(this);
        setContentView(webview);
        WebSettings browserSettings = webview.getSettings();
        browserSettings.setJavaScriptEnabled(true);
       // webview.loadUrl("http://slashdot.org/");
        webview.setWebViewClient(new WebViewClient());
  
        webview.loadUrl("http://www.google.com");

        //setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.action_bar, menu);
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
		//get which action bar item selected and perform action based on selection
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.refresh:
			Log.i("Main Activity", "Selected 'Refresh'");
			webview.reload();
			break;
			case R.id.backward:
			Log.i("Main Activity", "Selected 'Backward'");
			if (webview.canGoBack()) {
				webview.goBack();
			}
			break;
			case R.id.forward:
			Log.i("Main Activity", "Selected 'Forward'");
			if (webview.canGoForward()) {
				webview.goForward();
			}
			break;
		}
		return true;
	} 
    
}
