package com.testing.Test;

import java.util.Set;

import com.testing.appkw.KeywordOfApp;

public class H5Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("4723", "10000");
		app.runBrowser("6.0.1", "127.0.0.1:7555", "http://127.0.0.1:4723/wd/hub", "5000");
		app.driver.get("https://ui.ptlogin2.qq.com/cgi-bin/login?pt_hide_ad=1&style=9&pt_ttype=1&appid=549000929&pt_no_auth=1&pt_wxtest=1&daid=5&s_url=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex");
//		app.input("//*[@id=\"u\"]","12345");
		Set<String> contexts=app.driver.getContextHandles();
		for(String s:contexts) {
			System.out.println(s);
		}
		app.driver.context("NATIVE_APP");
		app.input("//android.webkit.WebView[@content-desc=\"手机统一登录\"]/android.widget.ListView/android.view.View[1]/android.widget.EditText", "12345");
		app.driver.context("CHROMIUM");
		app.input("//*[@id=\"p\"]","12345");
	}
}
