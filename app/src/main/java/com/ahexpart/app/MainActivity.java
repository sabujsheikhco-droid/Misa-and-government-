package com.ahexpart.app;
import android.app.Activity; import android.os.Bundle; import android.content.Intent; import android.net.Uri; import android.webkit.*;
public class MainActivity extends Activity {
 WebView web; ValueCallback<Uri[]> upload; static final int PICK=101;
 public void onCreate(Bundle b){super.onCreate(b); web=new WebView(this); setContentView(web);
 WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setAllowFileAccess(true); s.setAllowContentAccess(true);
 s.setAllowFileAccessFromFileURLs(false); s.setAllowUniversalAccessFromFileURLs(false);
 web.setWebViewClient(new WebViewClient()); web.setWebChromeClient(new WebChromeClient(){public boolean onShowFileChooser(WebView v,ValueCallback<Uri[]> cb,FileChooserParams p){upload=cb;try{startActivityForResult(p.createIntent(),PICK);return true;}catch(Exception e){upload=null;return false;}}});
 web.loadUrl("file:///android_asset/index.html");}
 protected void onActivityResult(int req,int res,Intent data){super.onActivityResult(req,res,data);if(req==PICK&&upload!=null){upload.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(res,data));upload=null;}}
 public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}