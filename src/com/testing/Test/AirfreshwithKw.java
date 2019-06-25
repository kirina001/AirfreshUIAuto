package com.testing.Test;

import com.testing.airfresh.AirfreshLogin;
import com.testing.airfresh.DevicesList;
import com.testing.airfresh.DevicesOperate;
import com.testing.appkw.KeywordOfApp;

public class AirfreshwithKw {

	public static void main(String[] args) {

		KeywordOfApp appkw = new KeywordOfApp();
		// 启动appium的服务端
		appkw.StartAppium("4723", "10000");
		// 运行APP
		appkw.runAPP("6.0.1", "127.0.0.1:7555", "com.FreshAir", ".activity.WelcomeActivity",
				"http://127.0.0.1:4723/wd/hub", "10000");
		appkw.wait("3000");

		// 登录
//		AirfreshLogin.LoginForCode(appkw, "15316093123","8888");
//		AirfreshLogin.LoginForPwd(appkw, "15316039123", "123456");
		// 设备详情操作
//		DevicesOperate.OnlineOperate(appkw);
//		DevicesOperate.CloseOperate(appkw);
		// 设备列表操作
		DevicesList.IntoDevicesList(appkw);// 进入设备列表
		DevicesList.GetOnline(appkw);//获取开机设备
//		DevicesList.InviteFriends(appkw,"13501796145");//邀请一个用户
//		DevicesList.EditDeviceName(appkw,"dev");//用户自定义设备名称
		DevicesList.EditDeviceByType(appkw, 2);
		
		// 登出
//		AirfreshLogin.LoginOut(appkw);

		// 根据自己需求对在线设备或离线设备进行测试

//		

	}

}
