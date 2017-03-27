package com.yushilei.myapp.ui;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.AndroidToJs;

import java.util.HashMap;
import java.util.Set;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.activity_web_view_web)
    WebView webView;
    @BindView(R.id.activity_web_view_web2)
    WebView webView2;
    @BindView(R.id.activity_web_view_web3)
    WebView webView3;
    @BindView(R.id.activity_web_view_web4)
    WebView webView4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onInitViews() {
        initWebAndroidCallJs();

        initWebJsCallAndroidByJavaScriptInterface();

        initWebJsCallAndroidByOverrideUrlLoading();

        initWebJsCallAndroidByChromeClient();
    }

    private void initWebJsCallAndroidByChromeClient() {

        WebSettings settings = webView4.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView4.setWebChromeClient(new WebChromeClient() {
            // 拦截输入框(原理同方式2)
            // 参数message:代表promt（）的内容（不是url）
            // 参数result:代表输入框的返回值
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                Log.i(getTAG(), "onJsPrompt :" + message);
                Uri uri = Uri.parse(message);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("demo")) {

                        //
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                        //参数result:代表消息框的返回值(输入值)
                        result.confirm("js调用了Android的方法成功啦");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            // 通过alert()和confirm()拦截的原理相同，此处不作过多讲述

            // 拦截JS的警告框
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.i(getTAG(), "onJsAlert :" + message);

                return super.onJsAlert(view, url, message, result);
            }

            // 拦截JS的确认框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });
        webView4.loadUrl("file:///android_asset/javascript4.html");
    }

    private void initWebJsCallAndroidByOverrideUrlLoading() {
        WebSettings settings = webView3.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView3.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(getTAG(), url);
                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {

                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上

                        Set<String> collection = uri.getQueryParameterNames();

                        StringBuilder sb = new StringBuilder();

                        for (String s : collection) {
                            sb.append(s).append(":").append(uri.getQueryParameter(s)).append(" ");
                        }
                        Toast.makeText(WebViewActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();

                    }

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView3.loadUrl("file:///android_asset/javascript3.html");
    }

    private void initWebJsCallAndroidByJavaScriptInterface() {
        WebSettings webSettings = webView2.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView2.addJavascriptInterface(new AndroidToJs(this), "test");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView2.loadUrl("file:///android_asset/javascript2.html");
    }

    private void initWebAndroidCallJs() {
        WebSettings webSettings = webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/javascript.html");
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
    }

    /**
     * Android调用JS ：loadUrl
     *
     * @param view
     */
    public void loadUrl(View view) {

        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:callJS()");
            }
        });
    }

    /**
     * Android调用JS ：evaluateJavaScript 4.4以上
     *
     * @param view
     */
    public void evaluateJs(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            // 只需要将第一种方法的loadUrl()换成下面该方法即可
            webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    //此处为 js 返回的结果
                    Toast.makeText(WebViewActivity.this, value, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
