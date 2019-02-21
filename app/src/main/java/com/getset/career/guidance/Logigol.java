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

public class Logigol extends AppCompatActivity {

    private ProgressDialog progressBar;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logigol);
        WebView webView = findViewById(R.id.logigol);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        progressBar = ProgressDialog.show(Logigol.this, "Loading", "Unleash the unique you...");
        time=-System.currentTimeMillis();

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
                writeToFile("Logical Aptitude 2 :"+url.substring(url.lastIndexOf('/')+1)+";",Logigol.this);
                writeToFile2("Logigol score : "+url.substring(url.lastIndexOf('/')+1)+"\n",Logigol.this);
                Intent intent = new Intent(Logigol.this, ReasoningAbility.class);
                Toast.makeText(getApplicationContext(),readFromFile2(Logigol.this),Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true; //don't allow WebView to load url
            }
        };
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("file:///android_asset/logigol/index.html");
        Button btn=findViewById(R.id.skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile("Logical Aptitude 2 :"+false+";",Logigol.this);
                time+=System.currentTimeMillis();
                writeToFile2("Logigol not solved.Time taken is :"+time+"\n",Logigol.this);
                Intent intent = new Intent(Logigol.this, ReasoningAbility.class);
                Toast.makeText(getApplicationContext(),readFromFile2(Logigol.this),Toast.LENGTH_LONG).show();
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
