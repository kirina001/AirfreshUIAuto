package com.testing.airfresh;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testing.appkw.KeywordOfApp;
import com.testing.auto.AutoLogger;

/**
 * 设备列表操作
 * 
 * @author hp 2019-05-31
 */
public class DevicesList {

	public static String response;

	/**
	 * 首页切换到设备管理列表
	 * 
	 * @param appkw
	 */
	public static void IntoDevicesList(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/main_menu");
		appkw.clickByid("com.FreshAir:id/menu_left_device_manager_layout");// 切换到设备管理列表
		appkw.wait("2000");
		appkw.adbSwipe(268, 142, 268, 720, 5);// 刷新
		appkw.wait("5000");
	}

	/**
	 * 从设备列表切换到首页
	 * 
	 * @param appkw
	 */
	public static void GotoMenu(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/left_button");// 切换到菜单
		appkw.adbTap("474", "573");// 切换到首页
		appkw.adbSwipe(283, 223, 283, 680, 5);// 刷新
		appkw.wait("5000");
	}

	/**
	 * 获取设备总数 可以封装将参数存入一个列表根据自己的需求选择 key对应value
	 * 
	 * @param appkw
	 * @return
	 */
	public static int GetDevicesNum(KeywordOfApp appkw) {
		IntoDevicesList(appkw);// 进入设备列表
//		appkw.wait("3000");
		int debicveNums = appkw.driver.findElements(By.id("com.FreshAir:id/item_my_device_list_switch")).size();// 存在数量操作5条统计不正确的bug
		AutoLogger.log.info("设备列表第一屏列表设备总数:" + debicveNums);
		return debicveNums;
	}

	/**
	 * 获取在线设备列表第一条数据
	 * 
	 * @param appkw
	 * @return
	 */
	@SuppressWarnings("unused")
	public static int GetOnline(KeywordOfApp appkw) {
		List<Integer> onlineList = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		// 获取列表设备状态元素
		List<WebElement> listDevices = appkw.driver.findElements(By.id("com.FreshAir:id/item_my_device_list_switch"));
		for (int i = 0; i < listDevices.size(); i++) {
			String status = listDevices.get(i).getText();
			if (status.equals("开启")) {
				onlineList.add(i);
			}
		}
		for (int i = 0; i < onlineList.size(); i++) {
			if (i == onlineList.size()) {
				AutoLogger.log.info("在线设备数量为0");
				return -1;
			} else {
				AutoLogger.log.info("在线设备总数：" + onlineList.size());
				AutoLogger.log.info("在线设备在总列表序号" + onlineList.get(0));// 取第一条在线数据
				int onlineDeviceIndex = onlineList.get(0);
				return onlineDeviceIndex;
			}
		}
		return -1;
	}

	// 离线状态需要区分
	/**
	 * 设备列表邀请好友
	 * 
	 * @param appkw
	 * @param phoneNum
	 */
	public static void InviteFriends(KeywordOfApp appkw, String phoneNum) {

		int index = ExpandMenu(appkw);// 展开第一个在线设备...
		appkw.clickByid("com.FreshAir:id/item_my_device_list_user");// 邀请用户
		appkw.wait("2000");
		appkw.inputByid("com.FreshAir:id/pop_add_share_phonenum", phoneNum);// 输入手机号码
		appkw.inputByid("com.FreshAir:id/pop_add_share_edit", "UI测试");// 好友名称
		appkw.clickByid("com.FreshAir:id/pop_add_share_add_btn");// 确认
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/item_my_device_list_close");// 关闭功能菜单
		String result = ShareInfoAdd();
		appkw.assertListText("com.FreshAir:id/item_my_device_list_content_hint", index, result);// 共享用户需要拼接
		appkw.saveScrShot("InviteFriends");
	}

	/**
	 * 用户自定义设备名称
	 * @param appkw
	 * @param deviceName 设备名称
	 */
	public static void EditDeviceName(KeywordOfApp appkw, String deviceName) {
		
		ExpandMenu(appkw);//展开菜单
		appkw.clickByid("com.FreshAir:id/item_my_device_list_edit");// 进入编辑功能
		appkw.wait("2000");
		appkw.inputByid("com.FreshAir:id/pop_change_device_edit", deviceName);// 修改设备名称
		appkw.clickByid("com.FreshAir:id/pop_change_device_confirm_btn");// 添加按钮
		appkw.wait("2000");
		appkw.assertListTextStart("com.FreshAir:id/item_my_device_list_title", deviceName);//校验设备列表存在修改后的名称
		AutoLogger.log.info("用户自定义设备名称方法执行完毕");
	}
	/**
	 * 使用默认设备名称
	 * @param appkw
	 * @param nameNum 设备名称序号
	 */
	public static void EditDeviceByType(KeywordOfApp appkw, int nameNum ) {
		ExpandMenu(appkw);
		appkw.clickByid("com.FreshAir:id/item_my_device_list_edit");// 进入编辑功能
		appkw.wait("2000");
		appkw.selectDeviceName("com.FreshAir:id/add_share_tv", nameNum);//选择系统默认名称
		appkw.clickByid("com.FreshAir:id/pop_change_device_confirm_btn");// 添加按钮
		appkw.wait("2000");
		String selectName = GetDeviceName(nameNum);//获取用户输入
		appkw.assertListTextStart("com.FreshAir:id/item_my_device_list_title", selectName);//校验设备列表存在修改后的名称
		AutoLogger.log.info("使用默认设备名称方法执行完毕");
	}
	
	/**
	 * 通过用户输入序号确认选择设备名称
	 * @param index
	 */
	public static String GetDeviceName(int index) {
		String deviceName = null;
		switch (index) {
			case 0:
				deviceName="主卧净化器";
				return deviceName;
			case 1:
				deviceName="客厅净化器";
				return deviceName;
			case 2:
				deviceName="厨房净化器";
				return deviceName;
			case 3:
				deviceName="次卧净化器";
				return deviceName;
			case 4:
				deviceName="浴室净化器";
				return deviceName;
			case 5:
				deviceName="书房净化器";
				return deviceName;
			default:
				deviceName="主卧净化器";
				return deviceName;
		}
	}

	/**
	 * 展开第一个在线设备列表右下角...
	 * 
	 * @param appkw
	 */
	public static int ExpandMenu(KeywordOfApp appkw) {
		int index = GetOnline(appkw);// 获取在线用户数
		if (index == -1) {
			AutoLogger.log.error("不存在开机设备");
			return -1;
		}
		response = appkw.getListTextByid("com.FreshAir:id/item_my_device_list_content_hint", index);// 初次进入设备列表时，在线设备的共享用户数
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_my_device_list_menu");// 设备列表
		listAll.get(index).click();// 展开右下角功能按钮
		return index;// 返回在线设备在设备列表的序号 0开始
	}

	/**
	 * 对邀请好友成功数据进行+1
	 * 
	 * @param appkw
	 */
	public static String ShareInfoAdd() {
		// 对校验数据进行
		String left = "已共享";
		String right = "用户";
		int num = Integer.parseInt(response.substring(3, 4));// 0开始
		int addNum = num + 1;
		String result = left + addNum + right;
		AutoLogger.log.info("处理后的共享用户数量：" + result);
		return result;
	}

}
