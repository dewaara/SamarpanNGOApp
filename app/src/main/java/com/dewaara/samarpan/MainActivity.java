package com.dewaara.samarpan;

/*
Samarpan HIT Haldia that App developed by Md.Halim from Dewaara Inc. 02/09/2019
 */

import android.app.DownloadManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public WebView mywebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mywebview = (WebView) findViewById( R.id.web );
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled( true );
        mywebview.getSettings().getAllowFileAccess(); // new add
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically( true ); // new add
        mywebview.getSettings().setSupportMultipleWindows( true ); // new add
        mywebview.loadUrl( "http://www.samarpanhit.in/" );

        mywebview.setDownloadListener( new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                DownloadManager.Request myRequest = new DownloadManager.Request( Uri.parse( url ) );
                myRequest.allowScanningByMediaScanner();
                myRequest.setNotificationVisibility( DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED );

                DownloadManager myManager = (DownloadManager) getSystemService( DOWNLOAD_SERVICE );
                myManager.enqueue( myRequest );

                Toast.makeText( MainActivity.this, "File is downloading...",Toast.LENGTH_SHORT ).show();
            }
        } );


        mywebview.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl( url );
                return true;
            }
        });

    }



    @Override
    public void onBackPressed() {
        if (mywebview.canGoBack()) {
            mywebview.goBack();
        } else super.onBackPressed();
    }

}


