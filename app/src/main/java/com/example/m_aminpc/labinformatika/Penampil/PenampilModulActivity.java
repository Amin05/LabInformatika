package com.example.m_aminpc.labinformatika.Penampil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.R;

public class PenampilModulActivity extends AppCompatActivity {
    WebView myWebView;
    private String url= Server.URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul);
        myWebView =findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                myWebView.loadUrl("javascript:(function() { " +
//                        "document.querySelector('[role=\"toolbar\"]').remove();})()");

            }
        });
        myWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i("ez",String.valueOf(newProgress));

            }


        });
        Intent intent= getIntent();
        url= url+"file_modul/"+intent.getStringExtra("file");
        //url= Server.URL2+"file/"+intent.getStringExtra("buku");

        myWebView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        Log.i("bebas","https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
