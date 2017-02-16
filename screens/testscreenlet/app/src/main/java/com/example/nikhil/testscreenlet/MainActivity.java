package com.example.nikhil.testscreenlet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.auth.login.interactor.LoginBasicInteractor;
import com.liferay.mobile.screens.auth.login.view.LoginViewModel;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView;

public class MainActivity extends AppCompatActivity implements LoginListener {
    public WebView mWebView;
    public String sessionCookie =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginScreenlet loginScreenlet = (LoginScreenlet)findViewById(R.id.login_screenlet);
        loginScreenlet.setListener(this);
    }

    @Override
    public void onLoginSuccess(User user) {

        Toast.makeText(MainActivity.this,
                "Login Success...", Toast.LENGTH_LONG).show();
        EditText pass = (EditText) this.findViewById(R.id.liferay_password);
        EditText email = (EditText) this.findViewById(R.id.liferay_login);

        WebView webview = new WebView(this);

        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        // Enable JavaScript.
        settings.setJavaScriptEnabled(true);
        // The two lines below are to enable interpreting <meta viewport> tag.
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webview.loadUrl("http://10.0.2.2:8080/c/portal/login?login="+email.getText()+"&password="+pass.getText());
       // webview.setWebViewClient(new MyWebViewClient ());
        setContentView(webview);

    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(MainActivity.this,
                "Login Fails...", Toast.LENGTH_LONG).show();
    }

}
