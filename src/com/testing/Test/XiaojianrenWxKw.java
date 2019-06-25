package com.testing.Test;

import com.testing.appkw.KeywordOfApp;

public class XiaojianrenWxKw {

	public static void main(String[] args) {
		
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("4723", "15000");//开启appium后台服务
		app.runAPP("6.0.1", "127.0.0.1:7555", 
				"com.tencent.mm", ".ui.LauncherUI", 
				"http://127.0.0.1:4723/wd/hub", "10000");//启动微信
		app.appiumSwipe("246", "143", "246", "600");//下滑
		app.click("//android.widget.FrameLayout[@content-desc=\"当前所在页面,与的聊天\"]/android.widget.FrameLayout/android.widget.LinearLayout"
				+ "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout"
				+ "/com.tencent.mm.ui.mogic.WxViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.FrameLayout"
				+ "/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]"
				+ "/android.widget.FrameLayout");//选择小荐人小程序

		
		
		
	}

}
