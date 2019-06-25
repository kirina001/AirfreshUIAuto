package com.testing.appkw;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.auto.AutoLogger;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class KeywordOfApp {
	public AndroidDriver driver;

	/**
	 * 空构造方法
	 * 
	 */
	public KeywordOfApp() {

	}

	/**
	 * 强制等待单位毫秒
	 * 
	 * @param time
	 */
	public void wait(String time) {
		int t = 0;
		try {
			t = Integer.parseInt(time);
			Thread.sleep(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 脚本执行CMD命令的函数
	 * 
	 * @param str
	 */
	public void runCmd(String str) {
		String cmd = "cmd /c start " + str;
		Runtime runtime = Runtime.getRuntime();
		try {
			AutoLogger.log.info("执行cmd命令:" + str);
			runtime.exec(cmd);
		} catch (Exception e) {
			AutoLogger.log.error("cmd命令执行失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 通过cmd启动appium的服务
	 * 
	 * @param port
	 * @param time
	 */
	public void StartAppium(String port, String time) {
		// 启动appium的服务端
		AutoLogger.log.info("启动appiumserver服务");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 当前时间的字符串
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String appiumLogFile = "SCRrun/" + createdate + "AppiumLog.txt";
		String startAppiumCMD = "appium -a 127.0.0.1 -p " + port + " --log " + appiumLogFile + " --local-timezone";
		runCmd(startAppiumCMD);
		try {
			int t = 1000;
			t = Integer.parseInt(time);
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动被测APP
	 * 
	 * @param platformVersion
	 * @param deviceName
	 * @param appPackage
	 * @param appActivity
	 * @param appiumServerIP
	 * @param time
	 */
	public void runAPP(String platformVersion, String deviceName, String appPackage, String appActivity,
			String appiumServerIP, String time) {
		try {
			AutoLogger.log.info("启动待测App");
			AppDriver app = new AppDriver(platformVersion, deviceName, appPackage, appActivity, appiumServerIP, time);
			driver = app.getdriver();
		} catch (Exception e) {
			AutoLogger.log.error("启动待测App失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 运行Android浏览器
	 * 
	 * @param platformVersion
	 * @param deviceName
	 * @param appiumServerIP
	 * @param waitTime
	 */
	public void runBrowser(String platformVersion, String deviceName, String appiumServerIP, String waitTime) {
		try {
			AutoLogger.log.info("启动安卓浏览器");
			BrowserDriver browser = new BrowserDriver(platformVersion, deviceName, appiumServerIP, waitTime);
			driver = browser.getdriver();
		} catch (Exception e) {
			AutoLogger.log.error("启动安卓浏览器失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 运行H5
	 * 
	 * @param url
	 */
	public void visitH5(String url) {
		try {
			AutoLogger.log.info("安卓浏览器访问" + url);
			driver.get(url);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 输入操作
	 * 
	 * @param xpath
	 * @param text
	 */
	public void input(String xpath, String text) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(text);
			AutoLogger.log.info("执行输入操作:" + xpath + "-----" + text);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 输入操作
	 * 
	 * @param id
	 * @param text
	 */
	public void inputByid(String id, String text) {
		try {
			explicityWaitId(id);
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(text);
			AutoLogger.log.info("执行Byid_input操作:" + id + "-----" + text);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 点击操作
	 * 
	 * @param xpath
	 */
	public void click(String xpath) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).click();
			AutoLogger.log.info("执行click操作：" + xpath);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 点击操作
	 */
	public void clickByid(String id) {
		try {
			explicityWaitId(id);
			driver.findElement(By.id(id));
			driver.findElement(By.id(id)).click();
			AutoLogger.log.info("执行Byid_click操作：" + id);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 通过id获取一组数据
	 * 
	 * @param id
	 * @return
	 */
	public List<WebElement> findElementsByid(String id) {
		@SuppressWarnings("unchecked")
		List<WebElement> listAll = driver.findElements(By.id(id));
		return listAll;
	}

	/**
	 * 调用adb滑动
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @param m
	 */
	public void adbSwipe(int i, int j, int k, int l, int m) {
		try {
			this.runCmd("adb shell input swipe " + i + " " + j + " " + k + " " + l + " " + m);
			AutoLogger.log.info("指定adb滑动操作" + i + "," + j + "," + k + "," + l + "," + m);
		} catch (Exception e) {
			AutoLogger.log.error("通过adb执行滑动失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 调用adb模拟按键
	 * 
	 * @param keycode
	 */
	public void adbPressKey(String keycode) {
		try {
			int k = Integer.parseInt(keycode);
			String cmd = " adb shell input keyevent " + k;
			runCmd(cmd);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			AutoLogger.log.error("通过adb执行按键事件失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 
	 * @param xAxis
	 * @param yAxis
	 */
	public void adbTap(String xAxis, String yAxis) {
		try {
			int x = Integer.parseInt(xAxis);
			int y = Integer.parseInt(yAxis);
			runCmd("adb shell input tap " + x + " " + y);
		} catch (Exception e) {
			AutoLogger.log.error("通过adb执行点击失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 退出app
	 */
	public void quitApp() {
		try {
			driver.closeApp();
		} catch (Exception e) {
			AutoLogger.log.error("关闭app失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 关闭appiumserver服务
	 */
	public void killAppium() {
		try {
			runCmd("taskkill /F /IM node.exe");
		} catch (Exception e) {
			AutoLogger.log.error("关闭appiumserver服务失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 获取文本信息
	 * 
	 * @param id
	 * @param paramRes
	 */

	public String getTextByid(String id) {
		try {
			explicityWaitId(id);
			String result = driver.findElement(By.id(id)).getText();
			AutoLogger.log.info("根据id获取text信息：" + result);
			return result;
		} catch (Exception e) {
			AutoLogger.log.error("根据id获取text信息报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
		return null;
	}

	/**
	 * 获取文本信息
	 * 
	 * @param id
	 * @param index 一组元素中的序号
	 */
	public String getListTextByid(String id, int index) {
		try {
			explicityWaitId(id);

			List<WebElement> listAll = findElementsByid(id);// 获取一组元素
			String result = listAll.get(index).getText();

			AutoLogger.log.info("根据id获取" + "第" + index + "个" + "text的信息：" + result);
			return result;
		} catch (Exception e) {
			AutoLogger.log.error("根据id获取text信息报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
		return null;
	}

	/**
	 * 断言不是某个字段则成功
	 * 
	 * @param id
	 * @param paramRes
	 */

	public void assertTextNot(String id, String not) {
		try {
			explicityWaitId(id);
			String result = driver.findElement(By.id(id)).getText();
			if (!result.equals(not)) {
				AutoLogger.log.info("测试用例执行成功");
			} else {
				AutoLogger.log.info("测试用例执行失败");
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 断言某个元素text是否以start开始
	 * 
	 * @param id
	 * @param start
	 */
	public void assertTextStart(String id, String start) {
		try {
			explicityWaitId(id);
			String result = driver.findElement(By.id(id)).getText();
			if (result.startsWith(start)) {
				AutoLogger.log.info("测试用例执行成功");
			} else {
				AutoLogger.log.info("测试用例执行失败");
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 断言某个元素text是否以start开始
	 * 
	 * @param id
	 * @param start
	 */
	public void assertListTextStart(String id, String start) {
		try {
			explicityWaitId(id);
			List<WebElement> listAll = findElementsByid(id);// 获取一组元素

			for (int x = 0; x < listAll.size(); x++) {
				String result = listAll.get(x).getText();// 存储获取的数据
				if (result.startsWith(start)) {
					AutoLogger.log.info("测试用例执行成功");
				} else {
					AutoLogger.log.info("测试用例执行失败");
				}
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}
	/**
	 * 根据用户输入序号选择对应设备名称
	 * @param id
	 * @param nameNum
	 */
	public void selectDeviceName(String id, int nameNum) {
		List<WebElement> listDeviceName = findElementsByid(id);// 默认设备名称列表

		for (int i=0; i < listDeviceName.size(); i++) {
			if (i == listDeviceName.size()) {
				AutoLogger.log.info("设备名称列表数量为0");
				return;
			} else {
				if(i==nameNum) {
					listDeviceName.get(i).click();
					AutoLogger.log.info("当前选择设备名称为：" + listDeviceName.get(i).getText());
				}
			}
		}
	}

	/**
	 * 根据id断言文本信息
	 * 
	 * @param id
	 * @param paramRes
	 */
	public Boolean assertText(String id, String expected) {
		try {
			explicityWaitId(id);
			String result = driver.findElement(By.id(id)).getText();
			AutoLogger.log.info("通过id获取text信息" + result);
			if (result.equals(expected)) {
				AutoLogger.log.info("测试用例执行成功");
				return true;
			} else {
				AutoLogger.log.info("测试用例执行失败");
				return false;
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
		return false;
	}

	/**
	 * 获取一组元素中的某个字段进行校验 id
	 * 
	 * @param id       元素id
	 * @param index    元素序号
	 * @param expected 期望值
	 */
	public Boolean assertListText(String id, int index, String expected) {
		try {
			explicityWaitId(id);
			List<WebElement> listAll = findElementsByid(id);// 获取一组元素
			String result = listAll.get(index).getText();//
			AutoLogger.log.info("通过id获取text信息：" + result);
			if (result.equals(expected)) {
				AutoLogger.log.info("测试用例执行成功");
				return true;
			} else {
				AutoLogger.log.info("测试用例执行失败");
				return false;
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
		return false;
	}

	/**
	 * 断言文本信息
	 * 
	 * @param xpath
	 * @param paramRes
	 */
	public void assertSame(String xpath, String paramRes) {
		try {
			explicityWait(xpath);
			String result = driver.findElement(By.xpath(xpath)).getText();
			System.out.println(result);
			if (result.equals(paramRes)) {
				AutoLogger.log.info("测试用例执行成功");
			} else {
				AutoLogger.log.info("测试用例执行失败");
			}
		} catch (Exception e) {
			AutoLogger.log.error("执行断言时报错");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 通过appium的方法进行滑屏
	 * 
	 * @param iniX
	 * @param iniY
	 * @param finX
	 * @param finY
	 */
	public void appiumSwipe(String iniX, String iniY, String finX, String finY) {
		try {
			int x = Integer.parseInt(iniX);
			int y = Integer.parseInt(iniY);
			int x1 = Integer.parseInt(finX);
			int y1 = Integer.parseInt(finY);
			TouchAction action = new TouchAction(driver);
			PointOption pressPoint = PointOption.point(x, y);
			PointOption movePoint = PointOption.point(x1, y1);
			action.longPress(pressPoint).moveTo(movePoint).release().perform();
		} catch (Exception e) {
			AutoLogger.log.error("执行Appium滑动方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 使用appium的方法点击坐标
	 * 
	 * @param x
	 * @param y
	 */
	public void appiumTap(String x, String y) {
		try {
			int xAxis = Integer.parseInt(x);
			int yAxis = Integer.parseInt(y);
			TouchAction action = new TouchAction(driver);
			PointOption pressPoint = PointOption.point(xAxis, yAxis);
			// action类分解动作，先长按，再移动到指定位置，再松开
			action.tap(pressPoint).release().perform();
		} catch (NumberFormatException e) {
			AutoLogger.log.error("执行Appium点击坐标方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 使用appium方法长按
	 * 
	 * @param x
	 * @param y
	 * @param time
	 */
	public void appiumHold(String x, String y, String time) {
		try {
			int xAxis = Integer.parseInt(x);
			int yAxis = Integer.parseInt(y);
			int t = Integer.parseInt(time);
			PointOption pressPoint = PointOption.point(xAxis, yAxis);
			Duration last = Duration.ofMillis(t);
			TouchAction action = new TouchAction(driver);
			action.longPress(pressPoint);
		} catch (NumberFormatException e) {
			AutoLogger.log.error("执行Appium滑动方法失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 实现显式等待的方法，在每次定位元素时，先尝试找元素，给10秒钟的最长等待。
	 */
	public void explicityWait(String xpath) {
		try {
			WebDriverWait eWait = new WebDriverWait(driver, 10);
			eWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 实现显式等待的方法，在每次定位元素时，先尝试找元素，给10秒钟的最长等待。
	 */
	public void explicityWaitId(String id) {
		try {
			WebDriverWait eWait = new WebDriverWait(driver, 10);
			eWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.id(id));
				}
			});
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 实现隐式等待的方法
	 */
	public void implicitlyWait() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 打印窗口名称
	 */
	public void printContexts() {
		Set<String> contexts = driver.getContextHandles();
		for (String s : contexts) {
			System.out.println(s);
		}
	}

	/**
	 * 切换窗口
	 * 
	 * @param contextName
	 */
	public void switchContext(String contextName) {
		try {
			AutoLogger.log.info("切换到" + contextName + "------context");
			driver.context(contextName);
		} catch (Exception e) {
			AutoLogger.log.error("切换context失败。");
		}
	}

	/**
	 * 保存截图
	 * 
	 * @param method
	 */
	public void saveScrShot(String method) {
		// 获取当前的执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 当前时间的字符串
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String scrName = "SCRshot/" + method + createdate + ".png";
		// 以当前文件名创建文件
		File scrShot = new File(scrName);
		// 将截图保存到临时文件
		File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tmp, scrShot);
		} catch (IOException e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("截图失败！");
		}
	}

}
