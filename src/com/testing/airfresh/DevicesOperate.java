package com.testing.airfresh;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.testing.appkw.KeywordOfApp;
import com.testing.auto.AutoLogger;
/**
 * 设备详情页操作
 * @author hp
 * 2019-05-30
 */
public class DevicesOperate {

	public static String time = null;

	/**
	 * 获取设备总数
	 * @param appkw
	 * @return
	 */
	public static int GetDevicesNum(KeywordOfApp appkw) {
		appkw.wait("3000");
		int debicveNums = appkw.driver.findElements(By.id("com.FreshAir:id/item_main_root_layout")).size();// 存在数量操作5条统计不正确的bug
		AutoLogger.log.info("首页第一屏列表设备总数:" + debicveNums);
		return debicveNums;
	}

	/**
	 * 获取在线设备列表第一条数据
	 * @param appkw
	 * @return
	 */
	@SuppressWarnings("unused")
	public static int GetOnline(KeywordOfApp appkw) {
		List<Integer> onlineList = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		// 获取列表设备状态元素
		List<WebElement> listDevices = appkw.driver.findElements(By.id("com.FreshAir:id/item_main_device_state"));
		for (int i = 0; i < listDevices.size(); i++) {
			String status = listDevices.get(i).getText();
			if (status.equals("设备在线")) {
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
	/**
	 * 获取第一条关闭设备
	 * @param appkw
	 * @return
	 */
	public static int GetClose(KeywordOfApp appkw) {
		List<Integer> closeList = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		// 获取列表设备状态元素
		List<WebElement> listDevices = appkw.driver.findElements(By.id("com.FreshAir:id/item_main_device_state"));
		for (int i = 0; i < listDevices.size(); i++) {
			String status = listDevices.get(i).getText();
			if (status.equals("设备关机")) {
				closeList.add(i);
			}
		}
		for (int i = 0; i < closeList.size(); i++) {
			if (i == closeList.size()) {
				AutoLogger.log.info("在线设备数量为0");
				return -1;
			} else {
				AutoLogger.log.info("关机设备总数：" + closeList.size());
				AutoLogger.log.info("关机设备在总列表序号" + closeList.get(0));// 取第一条在线数据
				int closeDeviceIndex = closeList.get(0);
				return closeDeviceIndex;
			}
		}
		return -1;
	}

	/**
	 * 对在线设备进行测试操作
	 * @param appkw
	 */
	public static void OnlineOperate(KeywordOfApp appkw) {

		GetDevicesNum(appkw);
		int index = GetOnline(appkw);
		if (index == -1) {
			AutoLogger.log.error("不存在在线设备");
			return;
		}
		// 首页设备列表
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_main_device_state");
		listAll.get(index).click();// 进入设备详情

		HistoryData(appkw);// 详情页查看历史数据
		Sleep(appkw);// 睡眠模式
		Intelligent(appkw);// 智能模式
		Auto(appkw);// 自动模式操作
		Locked(appkw);//童锁模式切换
		Filter(appkw);//滤芯复位
		SetLightStrip(appkw);//灯带操作
		SetTime(appkw);// 设置时间
		SeeTime(appkw);// 查看定时开关机详情
		DeleteTime(appkw);//删除一条定时开关机数据
		UpdateTime(appkw);// 编辑一条定时开关机数据

		GoToIndexList(appkw);//从详情页退回到首页列表

	}
	/**
	 * 从详情页退到首页列表
	 * @param appkw
	 */
	public static void GoToIndexList(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/left_button");
		appkw.wait("5000");
		AutoLogger.log.info("从详情页退到首页列表执行完毕");
	}

	/**
	 * 获取当前设备名称
	 * @param appkw
	 */
	public static void GetDeviceName(KeywordOfApp appkw) {
		appkw.getTextByid("com.FreshAir:id/title");
	}

	/**
	 * 详情页模式切换
	 * @param appkw
	 */
	public static void CloseModeSwitch(KeywordOfApp appkw) {
		GetDeviceName(appkw);
		appkw.clickByid("com.FreshAir:id/device_control_mode_sleep");// 睡眠
		appkw.wait("3000");
		// 能否获取toast信息
		appkw.clickByid("com.FreshAir:id/device_control_mode_smart");// 智能
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/device_control_mode_hands_control");// 自动
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/fm_device_control_locak");// 童锁
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/fm_device_control_filter");// 滤芯
		appkw.saveScrShot("CloseModeSwitch");// 截图保存待处理toast问题
		appkw.wait("3000");
		AutoLogger.log.info("执行菜单点击操作完毕");
	}

	/**
	 * 查看历史数据
	 * @param appkw
	 */
	public static void HistoryData(KeywordOfApp appkw) {
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/fm_device_control_layout");// 进入历史数据页面
		appkw.wait("3000");
		appkw.getTextByid("com.FreshAir:id/fm_history_pm_value");// 获取PM2.5数据
		appkw.clickByid("com.FreshAir:id/history_data_30_days_layout");// 点击30天数据
		appkw.wait("1000");
		appkw.getTextByid("com.FreshAir:id/fm_history_pm_value");// 获取PM2.5数据
		appkw.wait("1000");
		appkw.saveScrShot("HistoryData");
		appkw.clickByid("com.FreshAir:id/history_data_back");// 返回详情页
		appkw.wait("3000");
		AutoLogger.log.info("历史数据查看完毕");
	}

	/**
	 * 切换到睡眠模式
	 * @param appkw
	 */
	public static void Sleep(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/device_control_mode_sleep");
		appkw.wait("3000");
		appkw.saveScrShot("Sleep");
		AutoLogger.log.info("睡眠操作完毕");
	}

	/**
	 * 切换到智能模式
	 * @param appkw
	 */
	public static void Intelligent(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/device_control_mode_smart");
		appkw.wait("3000");
		appkw.saveScrShot("Intelligent");
		AutoLogger.log.info("智能操作完毕");
	}

	/**
	 * 切换到童锁模式
	 * @param appkw
	 */
	public static void Locked(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/fm_device_control_locak");
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/fm_device_control_locak");
		appkw.wait("2000");
		appkw.saveScrShot("Locked");
		AutoLogger.log.info("童锁操作完毕");
	}

	/**
	 * 查看滤芯信息
	 * @param appkw
	 */
	public static void Filter(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/fm_device_control_filter");
		appkw.wait("2000");
		appkw.assertTextStart("com.FreshAir:id/filter_title", "滤芯管理-");
		appkw.clickByid("com.FreshAir:id/filter_img_1");
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/filter_reset_btn");
		appkw.wait("5000");
		appkw.clickByid("android:id/button1");
		appkw.assertText("com.FreshAir:id/filter_cicle_one_tv_1", "100%");
		appkw.saveScrShot("Filter");
		appkw.clickByid("com.FreshAir:id/filter_back");
		AutoLogger.log.info("滤芯操作完毕");
	}

	/**
	 * 切换到自动模式
	 * @param appkw
	 */
	public static void Auto(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/device_control_mode_hands_control");
		appkw.wait("5000");
		appkw.adbTap("325", "220");// 通过坐标调节风量为3 不太合理分辨率不兼容如何处理
		appkw.wait("2000");
//		appkw.clickByid("	com.FreshAir:id/item_hands_control_fresh_air_mode_rb0");//新风常开
		appkw.clickByid("com.FreshAir:id/item_hands_control_fresh_air_mode_rb1");// 新风自动
//		appkw.wait("2000");
//		appkw.clickByid("com.FreshAir:id/item_hands_control_fu_heat_rb0");//辅热常开
		appkw.clickByid("com.FreshAir:id/item_hands_control_fu_heat_rb1");// 辅热自动
		appkw.wait("2000");
		appkw.saveScrShot("Auto");
		appkw.adbTap("161", "653");// 退出自动模式
		AutoLogger.log.info("自动模式操作完毕");
	}

	/**
	 * 灯带操作
	 * @param appkw
	 */
	public static void SetLightStrip(KeywordOfApp appkw) {
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/right_button");
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/pop_device_control_switch");
		appkw.wait("3000");
		appkw.saveScrShot("SetLightStrip");
		appkw.adbTap("259", "589");// 退出功能
		AutoLogger.log.info("灯带操作完毕");
	}

	/**
	 * 设置定时
	 * @param appkw
	 */
	public static void SetTime(KeywordOfApp appkw) {
		IntoSet(appkw);
		appkw.clickByid("com.FreshAir:id/right_button");// 添加时间
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/add_timer_open_device_tv");// 开机时间
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/add_timer_btn");// 系统当前时间
		time = appkw.getTextByid("com.FreshAir:id/add_timer_open_device_tv");// 设置开机时间
		appkw.clickByid("com.FreshAir:id/add_timer_close_device_layout");// 关机时间
		appkw.wait("2000");
//		appkw.adbTap("401", "812");
		appkw.adbTap("186", "811");
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/add_timer_btn");// 确认
		appkw.wait("2000");
		appkw.clickByid("com.FreshAir:id/right_button");// 保存
		appkw.wait("2000");
		appkw.adbSwipe(220, 274, 250, 785, 5);// 滑动刷新数据
		appkw.wait("2000");
		AssertTime(appkw);// 校验新增时间是否一致
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/left_button");// 返回详情页
		AutoLogger.log.info("设置定时开关机执行完毕");
	}

	/**
	 * 删除第一条定时开关机数据
	 * @param appkw
	 */
	public static void DeleteTime(KeywordOfApp appkw) {
		IntoSet(appkw);
		List<WebElement> listAll = GetTimeList(appkw);
		if (listAll.size() != 0) {// 如果存在定时开关机数据
			listAll.get(0).click();// 点击进入第一条数据详情
			appkw.clickByid("com.FreshAir:id/add_timer_delete");// 点击删除按钮
			appkw.wait("5000");
			appkw.saveScrShot("DeleteTime");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回详情页面
			AutoLogger.log.info("删除一条定时开关机数据");
		} else {
			AutoLogger.log.info("时间列表为空");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回详情页面
			return;
		}
	}

	/**
	 * 修改定时开关机
	 * 
	 * @param appkw
	 */
	public static void UpdateTime(KeywordOfApp appkw) {
		IntoSet(appkw);
		List<WebElement> listAll = GetTimeList(appkw);
		if (listAll.size() != 0) {// 如果存在定时开关机数据
			listAll.get(0).click();// 点击进入第一条数据详情
			appkw.clickByid("com.FreshAir:id/add_timer_boot_switch");// 将开机状态进行更改
			appkw.wait("5000");
			String cc = appkw.getTextByid("com.FreshAir:id/add_timer_boot_switch");// 记住开机时间状态更改后的数据
			System.out.println("状态更改后的数据：" + cc);
			// 进入前校验一次开机状态
			// 修改后校验一次
			// 根据开机时间到设备列表进行状态校验
			appkw.clickByid("com.FreshAir:id/right_button");// 保存
			appkw.wait("3000");
			appkw.saveScrShot("UpdateTime");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回详情页面
			AutoLogger.log.info("修改一条定时开关机数据");
		} else {
			AutoLogger.log.info("时间列表为空");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回详情页面
			return;
		}
	}

	/**
	 * 从详情页进入设置功能
	 * 
	 * @param appkw
	 */
	public static void IntoSet(KeywordOfApp appkw) {
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/right_button");
		appkw.wait("3000");
		appkw.clickByid("com.FreshAir:id/pop_device_control_timer_switch");
		appkw.wait("3000");
		AutoLogger.log.info("从详情页，进入设置功能完毕");
	}

	/**
	 * 查看定时开关机详情
	 * 
	 * @param appkw
	 */
	public static void SeeTime(KeywordOfApp appkw) {
		IntoSet(appkw);// 进入定时开关机列表页面
		List<WebElement> listAll = GetTimeList(appkw);// 进入第一个定时开关机详情

		if (listAll.size() != 0) {// 如果存在定时开关机数据
			listAll.get(0).click();// 点击进入第一条数据详情
			appkw.wait("2000");
			appkw.assertText("com.FreshAir:id/title", "添加定时");
			appkw.wait("2000");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回时间列表
			appkw.wait("2000");
			appkw.clickByid("com.FreshAir:id/left_button");// 返回设备详情页
			appkw.wait("2000");
			AutoLogger.log.info("查看定时开关机详情页方法执行完毕");
		} else {
			AutoLogger.log.info("时间列表为空,查看详情方法执行完毕");
		}
		appkw.saveScrShot("SeeTime");

	}

	/**
	 * 定时开关机列表
	 * 
	 * @param appkw
	 */
	public static List<WebElement> GetTimeList(KeywordOfApp appkw) {
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_timer_switch_root_layout");// 开始时间列表
		appkw.saveScrShot("GetTimeList");
		return listAll;
	}

	/**
	 * 定时开关机已关机\开机列表
	 * 
	 * @param appkw
	 */
	public static List<WebElement> GetTimeStatusList(KeywordOfApp appkw) {
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_timer_switch_boot_period");// 开始时间列表
		appkw.saveScrShot("GetTimeStatusList");
		return listAll;
	}

	/**
	 * 校验时间设置
	 * 
	 * @param appkw
	 */
	public static void AssertTime(KeywordOfApp appkw) {
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_timer_switch_boot_time");// 开始时间列表
		// 判定列表包含有新增的开始时间
		try {
			for (int index = 0; index < listAll.size(); index++) {
				if (time.equals(listAll.get(index).getText())) {
					AutoLogger.log.info("时间列表有相同的时间，测试成功");
				}
			}
			appkw.saveScrShot("AssertTime");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 操作关机设备 封装处理
	 * @param appkw
	 */
	public static void CloseOperate(KeywordOfApp appkw) {
		GetDevicesNum(appkw);
		int index = GetClose(appkw);
		if (index == -1) {
			AutoLogger.log.error("不存在关机设备");
			return;
		}
		// 首页设备列表
		List<WebElement> listAll = appkw.findElementsByid("com.FreshAir:id/item_main_device_state");
		listAll.get(index).click();// 进入设备详情
		AutoLogger.log.info("从首页进入设备详情页");
		appkw.wait("2000");
		appkw.assertText("com.FreshAir:id/fm_device_control_co_value", "--");// 校验CO2信息是否显示
		appkw.wait("3000");

		CloseModeSwitch(appkw);//执行各种点击操作
		SetLightStrip(appkw);//灯带操作
		SetTime(appkw);//设置定时开关机
		HistoryData(appkw);// 详情页查看历史数据
		GoToIndexList(appkw);//从详情页退回首页列表

	}
	/**
	 * 执行开机操作
	 * @param appkw
	 */
	public static void OpenDevices(KeywordOfApp appkw) {
		appkw.clickByid("com.FreshAir:id/device_control_switch");
		appkw.wait("6000");
		appkw.assertTextNot("com.FreshAir:id/fm_device_control_co_value", "--");// 校验CO2信息是否显示
		appkw.saveScrShot("OperateClose");
		AutoLogger.log.info("设备开机操作执行完毕");
	}

}
