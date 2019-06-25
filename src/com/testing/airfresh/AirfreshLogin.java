package com.testing.airfresh;

import com.testing.appkw.KeywordOfApp;
/**
 * 登录登出
 * @author hp
 * 2019-05-29
 */
public class AirfreshLogin {

	/**
	 * 验证码登录 for id
	 * @param appkw
	 * @param phone 手机号码
	 */
	public static void LoginForCode(KeywordOfApp appkw,String phone,String code) {
		//切换为验证码登录
//		Boolean loginType = appkw.assertText("com.FreshAir:id/login_by_pwd_code", "验证码登录");
//		if(loginType) {
//			
//			appkw.wait("2000");
//			System.out.println(loginType);
//		}
		
		appkw.clickByid("com.FreshAir:id/login_by_pwd_code");
		appkw.inputByid("com.FreshAir:id/login_by_code_phone", phone);
		appkw.inputByid("com.FreshAir:id/login_by_code_number", code);
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/login_by_code_login");
		appkw.wait("5000");
		appkw.adbSwipe(264, 267, 250, 664, 5);
		appkw.wait("3000");
		
	}
	/**
	 * 密码登录 for id
	 * @param appkw
	 * @param phone 手机号码
	 * @param pwd 密码
	 */
	public static void LoginForPwd(KeywordOfApp appkw,String phone,String pwd) {
		appkw.inputByid("com.FreshAir:id/login_by_pwd_phone", phone);
		appkw.inputByid("com.FreshAir:id/login_by_pwd", pwd);
		appkw.clickByid("com.FreshAir:id/login_by_pwd_login");
		appkw.wait("3000");
		appkw.adbSwipe(264, 267, 250, 664, 5);
		appkw.wait("3000");
		
	}

	/**
	 * 退出 for idc从主页列表点击菜单退出
	 * @param appkw
	 */
	public static void LoginOut(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/main_menu");
		appkw.clickByid("com.FreshAir:id/menu_left_user_icon");
		appkw.clickByid("com.FreshAir:id/fm_phone_quite");
		appkw.clickByid("android:id/button1");
	}

}
