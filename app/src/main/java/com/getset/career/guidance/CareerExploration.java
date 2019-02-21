package com.getset.career.guidance;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import com.getset.career.guidance.*;
import com.google.android.gms.common.internal.Constants;


public class CareerExploration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_exploration);
        String mUrl="http://niewlabs.github.io/";
        final WebView webview=findViewById(R.id.exploration);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.setHorizontalScrollBarEnabled(true);
        webview.setVerticalScrollBarEnabled(true);
        webview.setInitialScale(1);
        webview.setVisibility(View.INVISIBLE);
        webview.zoomOut();
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // hide element by class name
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('navbar')[0].style.display='none'; })()");
                webview.setVisibility(View.VISIBLE);
            }
        });

        webview.loadUrl(mUrl);
        webview.setOnFocusChangeListener(new MyOnFocusChangeListener(getScale()));
    }
    private float getScale() {
        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        Float val = Float.valueOf(width) / Float.valueOf(Resources.getSystem().getDisplayMetrics().widthPixels);
        return val.floatValue();
    }
}
