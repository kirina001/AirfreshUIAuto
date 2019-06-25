package com.testing.Test;

import java.util.Set;

import com.testing.appkw.KeywordOfApp;

public class WxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp app=new KeywordOfApp();
		app.StartAppium("4723", "11000");
		app.runAPP("6.0.1", "127.0.0.1:7555", "com.tencent.mm", ".ui.LauncherUI", "http://127.0.0.1:4723/wd/hub", "11000");
		app.appiumSwipe("300", "300", "300", "600");
		app.click("//android.widget.FrameLayout[@content-desc=\"当前所在页面,与青鸿的聊天\"]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/com.tencent.mm.ui.mogic.WxViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.widget.ImageView[2]");
		app.wait("10000");
		Set<String> set=app.driver.getContextHandles();
		for(String s:set) {
			System.out.println(s);
		}
	}

}
