package com.client.weeat.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.client.weeat.R;

import static com.client.weeat.util.Constants.BASIC_URL;

public class LoadWebActivity extends AppCompatActivity {
    private WebView webView;
    private int webviewID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_load_web);
        try {
            webviewID = (int) getIntent().getSerializableExtra("webviewID");
            Log.e("fragmentID", webviewID+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        webView = (WebView)findViewById(R.id.wv_webview);
        loadWeb();
    }
    public void loadWeb(){
        //根据回传编号选择html5
        String url = "https://www.baidu.com/";
        switch (webviewID) {
            case 0:
                url="http://www.iconfont.cn/";
                break;
            case 1:
                url=BASIC_URL+"/FirstHtml.html";
                break;
            case 2:
                url=BASIC_URL+"/SecondHtml.html";
                break;
            case 3:
                url=BASIC_URL+"/ThirdHtml.html";
                break;
            case 4:
                url=BASIC_URL+"/ForthHtml.html";
                break;
            case 5:
                url=BASIC_URL+"/FifthHtml.html";
                break;

        }
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
