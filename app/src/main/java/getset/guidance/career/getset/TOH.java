package getset.guidance.career.getset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TOH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toh);
        WebView webView = findViewById(R.id.toh);
        webView.getSettings().setJavaScriptEnabled(true);
        WebViewClient mWebViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(TOH.this, Home.class);
                intent.putExtra("Time for Memory Game",url);
                startActivity(intent);
                return true; //don't allow WebView to load url
            }
        };
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("file:///android_asset/TOH/index.html");
    }
}
