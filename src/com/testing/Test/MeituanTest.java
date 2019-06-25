package com.testing.Test;

import com.testing.appkw.KeywordOfApp;

public class MeituanTest {

	public static void main(String[] args) {
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("4723", "15000");
		app.runAPP("6.0.1", "127.0.0.1:7555", 
				"com.tencent.mm", ".ui.LauncherUI", 
				"http://127.0.0.1:4723/wd/hub", "10000");
		app.appiumSwipe("246", "143", "246", "600");
//		app.click("//android.widget.FrameLayout[@content-desc=\"当前所在页面,与青鸿的聊天\"]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/com.tencent.mm.ui.mogic.WxViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.widget.ImageView[2]");
//		app.wait("10000");
//		app.printContexts();
//		app.click("//android.view.View[@content-desc=\"甜点饮品\"]");
//		app.saveScrShot("test");
	}

}
