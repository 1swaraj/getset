package com.getset.career.guidance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RapidEvaluationAbility extends AppCompatActivity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rapid_evaluation_ability);
        WebView webView = findViewById(R.id.rea);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        progressBar = ProgressDialog.show(RapidEvaluationAbility.this, "Loading", "Unleash the unique you...");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        WebViewClient mWebViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                writeToFile("REA :"+url.substring(url.indexOf("com")+3,url.lastIndexOf('/'))+";",RapidEvaluationAbility.this);
                writeToFile2("REA score : "+url.substring(url.indexOf("com")+3,url.lastIndexOf('/'))+"\n",RapidEvaluationAbility.this);
                Intent intent = new Intent(RapidEvaluationAbility.this, Selection.class);
                String x = readFromFile2(RapidEvaluationAbility.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true; //don't allow WebView to load url
            }
        };
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("file:///android_asset/REA/index.html");
        Button btn=findViewById(R.id.skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile("REA :"+false+";",RapidEvaluationAbility.this);
                writeToFile2("REA not attempted\n",RapidEvaluationAbility.this);
                Intent intent = new Intent(RapidEvaluationAbility.this, Selection.class);
                String x = readFromFile2(RapidEvaluationAbility.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private String readFromFile2(Context context) {
        String contents="";
        try {
            File file = new File(context.getFilesDir(), "report.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
            contents = new String(bytes);
        }
        catch (Exception e)
        {
            Log.e("PDFCreator", "createPDFError:" + e);

        }
        return contents;
    }


    private void writeToFile2(String data,Context context) {
        try {
            String MYFILE = "report.txt";
            FileOutputStream fos;
            File file = new File(context.getFilesDir(), MYFILE);
            if(file.exists()) {
                FileOutputStream stream = new FileOutputStream(file,true);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
            else {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
        } catch (IOException e) {
            e.toString();
        }
    }


    private void writeToFile(String data,Context context) {
        try {
            String MYFILE = "config.txt";
            FileOutputStream fos;
            File file = new File(context.getFilesDir(), MYFILE);
            if(file.exists()) {
                FileOutputStream stream = new FileOutputStream(file,true);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
            else {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
        } catch (IOException e) {
            e.toString();
        }
    }
}
