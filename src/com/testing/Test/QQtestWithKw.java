package com.testing.Test;
import org.openqa.selenium.By;
import com.testing.appkw.KeywordOfApp;

import io.appium.java_client.MobileBy;


public class QQtestWithKw {

	public static void main(String[] args) throws Exception {

		KeywordOfApp appkw= new KeywordOfApp();
		//启动appium的服务端
		appkw.StartAppium("4723", "10000");
		//运行APP
		appkw.runAPP("6.0.1", "127.0.0.1:7555",
				"com.tencent.qqlite", "com.tencent.mobileqq.activity.SplashActivity", "http://127.0.0.1:4723/wd/hub", "10000");
		appkw.wait("2000");
		//登录
		appkw.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Button[1]")).click();
		appkw.wait("1000");
		appkw.driver.findElement(MobileBy.AccessibilityId("请输入QQ号码或手机或邮箱")).clear();
		appkw.driver.findElement(MobileBy.AccessibilityId("请输入QQ号码或手机或邮箱")).sendKeys("1939635731");
		appkw.driver.findElement(MobileBy.AccessibilityId("请输入密码")).clear();
		appkw.driver.findElement(MobileBy.AccessibilityId("请输入密码")).sendKeys("18783405174");
		appkw.driver.findElement(MobileBy.AccessibilityId("登录QQ")).click();
		appkw.wait("2000");
		appkw.appiumSwipe("224", "320", "210", "772");//下滑刷新数据
		appkw.wait("5000"); 
		
		//点击搜索按钮
		//通过resource-id定位元素
		appkw.driver.findElement(MobileBy.AccessibilityId("搜索")).click();
		appkw.input("//android.widget.RelativeLayout[@content-desc='搜索聊天或者联系人']/android.widget.EditText", "chen");
		//输入搜索信息
		appkw.adbPressKey("66");
		appkw.wait("2000");
		//识别方式不合理 后期会变化
		appkw.driver.findElement(By.id("com.tencent.qqlite:id/icon")).click();
//		appkw.click("//android.widget.RelativeLayout[@content-desc='陈娟,[图片],上午10:52']/android.widget.ImageView");
		appkw.wait("1000");
		//点击聊天设置
		appkw.click("//android.widget.ImageView[@content-desc='聊天设置']");
		appkw.wait("1000");
		//查看个人资料
		appkw.click("//android.widget.RelativeLayout[@content-desc='查看个人资料']/android.widget.ImageView");
		appkw.wait("1000");
		//进入qq空间
		appkw.click("//android.widget.LinearLayout[@content-desc='QQ空间']/android.widget.TextView[1]");
		appkw.wait("5000");
		//向上滑动屏幕
		appkw.appiumSwipe("308", "825", "262", "152");
		appkw.wait("1000");
		//点赞
		appkw.click("//android.view.View[@content-desc='赞']");
		//评论
		appkw.click("//android.view.View[@content-desc='评论']");
		appkw.wait("2000");
		//通过class定位元素
//		appkw.driver.findElement(By.className("android.widget.EditText")).sendKeys("2222");		
		appkw.wait("1000");
		//通过content-desc定位元素
//		appkw.driver.findElementByAccessibilityId("发表");
//		appkw.driver.findElement(By.className("android.widget.Button")).click();
//		appkw.adbTap("495", "121");
//		[460,97][531,142]
//				495,121
		System.out.println("执行完毕");

		
		


//	
//		//点击搜索按钮
//		appkw.click("//android.widget.EditText[@resource-id='com.tencent.mobileqq:id/et_search_keyword']");
//		//在搜索框中输入内容
//		appkw.input("//android.widget.EditText[@resource-id='com.tencent.mobileqq:id/et_search_keyword']", "578225840");
//		//点击头像
//		appkw.click("//android.widget.RelativeLayout[@resource-id='com.tencent.mobileqq:id/name']/android.widget.ImageView[1]");
//		//点击设置
//		appkw.click("//android.widget.ImageView[@resource-id='com.tencent.mobileqq:id/ivTitleBtnRightImage']");
//		//点击信息栏
//		appkw.click("//android.widget.LinearLayout[@resource-id='com.tencent.mobileqq:id/name']/android.widget.RelativeLayout[1]");
//		//特权
//		appkw.click("//android.widget.TextView[@text='TA还未开通任何特权服务']");
//		appkw.wait("5000");
//		//向上滑动屏幕
//		appkw.appiumSwipe("300", "650", "300", "200");
//		appkw.click("//android.view.View[@resource-id='service_vip']/android.view.View[3]/android.widget.Button[1]");
//		appkw.quitApp();
//		appkw.killAppium();
	}
		
}
