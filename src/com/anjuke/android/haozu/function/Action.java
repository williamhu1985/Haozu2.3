package com.anjuke.android.haozu.function;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anjuke.android.util.ScreenShot;
import com.anjuke.android.util.Report;
import com.anjuke.android.haozu.R;
import com.anjuke.android.haozu.test.*;
import com.jayway.android.robotium.solo.Solo;
import static junit.framework.Assert.*;

public class Action {

	/**
	 * 点击定位框的取消并选择上海
	 * @param solo
	 */

	public static void alertCancleButton(Solo solo) {
		if (solo.searchButton("取消")) {
			clickOnText(solo, "取消", "定位框的取消");
			Action.clickOnText(solo, "上海", "城市：上海");
		}
	}

	/**
	 * 多值匹配比较 主要用于筛选条件较多的情况
	 * 
	 * @param solo
	 * @param message
	 *            比较内容
	 * @param searchTip
	 *            信息栏显示的筛选条件
	 * @param params
	 *            筛选选择的条件
	 * @return
	 */
	public static boolean assertFilter(Solo solo, String message,
			String searchTip, String... params) {
		String str = "";
		String expect = "";
		for (String a : params) {
			if (a.equals("不限") == false) {
				if (searchTip.indexOf(a) < 0) {
					str += a + " ";
				}
				expect += a + " ";
			}
		}
		if (str == "") {
			Report.writeHTMLLog("比较","【"+ message +"】"+ "一致，value="+searchTip, "PASS", Action.screenShot(solo));
			System.out.println("比较结果一致---->");
			return true;
		} else {
			Report.writeHTMLLog("比较","【"+ message +"】"+"不一致,expect=" + expect
					+ "receive=" + searchTip, "FAIL", Action.screenShot(solo));
			System.out.println("比较结果不一致---->");
			return false;
		}
	}

	/**
	 * 比较两个字符是否一致
	 * 
	 * @param message
	 *            比较的内容
	 * @param expect
	 *            期望值
	 * @param receive
	 *            接收到的值
	 */

	public static void assertString(Solo solo, String message, String expect,
			Object receive) {
		//		 String imag =screenShot(solo);
		String imag = "";

		if (expect.equals(receive)) {
			Report.writeHTMLLog("比较","【"+ message +"】" + "一致,value="+expect, "PASS", imag);
		} else {
			Report.writeHTMLLog("比较","【"+ message +"】"+ "不一致,expect=" + expect
					+ "receive:" + receive, "FAIL", imag);
			fail();
		}
	}

	/**
	 * 用id点击
	 * 
	 * @param solo
	 * @param id
	 *            资源id
	 * @param message
	 *            点击控件的名字
	 */
	public static void clickById(Solo solo, int id, String message) {
		String detail = "点击" +"【"+ message +"】";
		//		 String imag =screenShot(solo);
		String imag = "";
		try {
			// solo.clickOnView(solo.getCurrentActivity().findViewById(id));
			solo.clickOnView(solo.getView(id));
			Report.writeHTMLLog("点击", detail, "DONE", imag);
		} catch (NoSuchElementException e) {
			Report.writeHTMLLog("点击", detail, "FAIL", imag);
		}
	}

	/**
	 * 筛选
	 * 
	 * @param solo
	 * @param index
	 *            某个筛选条件下的下标
	 * @param message
	 *            筛选条件
	 * @return 选中的字符串
	 * @throws InterruptedException
	 */
	public static String clickInFilter(Solo solo, int index, String message){
		ArrayList<View> obj = null;
		if (message.equals("租金")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_price_rg));
		} else if (message.equals("户型")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_room_type_rg));
		} else if (message.equals("来源")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_from_rg));
		} else if (message.equals("方式")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_rent_method_rg));
		} else {
			Report.writeHTMLLog("点击", "筛选类型不存在", "WARNING", "");
			fail("筛选类型不存在");
		}	

		for (int i = 1; i <index; i++) {
			clickOnView(solo, obj.get(i), message+"："+((TextView) obj.get(i)).getText().toString());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clickOnView(solo, obj.get(index), message+"------->"+((TextView) obj.get(index)).getText().toString());
		// clickById(solo, obj.get(index).getId(),
		// ((TextView)obj.get(index)).getText().toString());
		return ((TextView) obj.get(index)).getText().toString();
	}

	/**
	 * 设置找房筛选条件 
	 * @param solo
	 * @param priceIndex
	 * @param typeIndex
	 * @param fromIndex         
	 * @param methodIndex
	 * @return flag
	 * 
	 */
	public static void selectFilterCondition(Solo solo, int priceIndex, int typeIndex, int fromIndex, int methodIndex){
		Boolean flag = false;;
		String searchTip = "";
		String price = clickInFilter(solo,priceIndex,"租金");
		//过滤价格里的空格
		if(price.indexOf("-")>=0){
			price=price.substring(0, price.indexOf("-")-1)+"-"+price.substring(price.indexOf("-")+2,price.length());
		}
		String type  = clickInFilter(solo,typeIndex,"户型");
		String source = clickInFilter(solo,fromIndex,"来源");
		String rentType = clickInFilter(solo,methodIndex,"方式");
		solo.clickOnButton("确定");
//		int count = 0; 
//		while(count<=5){
//			if(solo.searchText("正在刷新")){
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				count ++;
//			}
//		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Action.wait(solo, R.id.activity22_search_list_searchtip, 5, "等待searchTip显示");
		//获取搜索栏信息
		if(solo.searchText("地图")){
			searchTip = (String) ((TextView) solo
					.getView(R.id.activity22_nearby_list_searchtip)).getText();
		}else if(solo.searchText("切换城市")){
			searchTip = (String) ((TextView) solo
					.getView(R.id.activity22_search_list_searchtip)).getText();
		}

		System.out.println("搜索栏信息为：" + searchTip+ "---->");
		Report.writeHTMLLog("好租V2.2搜索页筛选条件测试用例1", "搜索栏信息为："+ searchTip, Report.DONE, "");
		flag = Action.assertFilter(solo, "筛选条件和筛选信息栏", searchTip, price, type,
				source, rentType);
		if(flag){
			Report.writeHTMLLog("设置筛选找房条件", "筛选找房条件设置成功", Report.PASS, "");				
		}else{
			String ps = Action.screenShot(solo);
			Report.writeHTMLLog("好租搜索页筛选条件测试用例3", "筛选条件匹配成功，不是预期结果,用例执行失败---->", Report.FAIL, ps);				
		    fail("筛选找房条件设置失败---->");
		}
	}	


	/*
	 筛选
	 * 
	 * @param solo
	 * @param index
	 *            某个筛选条件下的下标
	 * @param message
	 *            筛选条件
	 * @return 检查某个radioGroup的index项是否被选中
	 * @throws InterruptedException
	 */

	public static boolean checkInFilter(Solo solo,int index, String message){
		ArrayList<View> obj = null;
		Boolean flag = false;

		if (message.equals("租金")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_price_rg));
		} else if (message.equals("户型")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_room_type_rg));
		} else if (message.equals("来源")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_from_rg));
		} else if (message.equals("方式")) {
			obj = solo.getViews(solo
					.getView(R.id.activity_find_room_filter_v2_rent_method_rg));
		} else {
			Report.writeHTMLLog("点击", "筛选类型不存在", "WARNING", "");
			fail("筛选类型不存在");
		}		
		flag = ((RadioButton)obj.get(index)).isChecked();		

		return flag;
	}



	/**
	 * 检查当前显示的筛选条件是否与上次的一致
	 * 
	 * @param solo
	 * @param priceIndex
	 * @param typeIndex
	 * @param fromIndex         
	 * @param methodIndex
	 * @return flag
	 * @
	 */
	public static Boolean checkFilterCondition(Solo solo, int priceIndex, int typeIndex, int fromIndex, int methodIndex){
		Boolean flag = false;;
		Boolean priceFlag = checkInFilter(solo,priceIndex,"租金");
		Boolean typeFlag  = checkInFilter(solo,typeIndex,"户型");
		Boolean fromFlag  = checkInFilter(solo,fromIndex,"来源");
		Boolean methodFlag = checkInFilter(solo,methodIndex,"方式");
		if(priceFlag&&typeFlag&&fromFlag&&methodFlag){
			flag = true;
		}
		return flag;
	}



	/**
	 * 点击文字
	 * 
	 * @param solo
	 * @param name
	 *            控件显示的字
	 * @param message
	 *            相关信息
	 */
	public static void clickOnText(Solo solo, String name, String message) {
		// String imag =screenShot(solo);
		String imag = "";
		try {
			solo.clickOnText(name);
			Report.writeHTMLLog("点击", "点击" +"【"+ message +"】", "DONE", imag);
		} catch (NotFoundException e) {
			Report.writeHTMLLog("点击", "点击" +"【"+ message +"】", "Fail", imag);
		}
	}

	/**
	 * 点击view
	 * 
	 * @param solo
	 * @param view
	 *            需要点击的view
	 * @param message
	 *            相关信息
	 */
	public static void clickOnView(Solo solo, View view, String message) {
		String imag = "";
		String detail = "点击" + "【"+ message +"】";
		try {
			solo.clickOnView(view);
			Report.writeHTMLLog("点击", detail, "DONE", imag);
		} catch (NotFoundException e) {
			Report.writeHTMLLog("点击", detail, "FAIL", imag);
		}
	}

	/**
	 * 返回主页
	 * 
	 * @param solo
	 */
	public static void goHomePage(Solo solo) {
		// String imag =screenShot(solo);
		String imag = "";
		CharSequence name = "";
		while (solo.searchButton("菜单") == false
				|| solo.searchButton("地图") == false) {
			name = solo.getButton(0).getText();

			solo.clickOnButton(0);
			Report.writeHTMLLog("点击", "点击" +"【"+ name+"】", "DONE", imag);
		}
	}
	/**
	 * 初始化筛选条件
	 * @param solo
	 */
//	public static void init(Solo solo){
//		pathLineMemu(solo, 2);
//		clickById(solo, R.id.activity_search_address_city,"切换城市");
//		clickOnText(solo, "成都", "成都");
//		pathLineMemu(solo, 3);
//		clickById(solo, R.id.activity_find_room_filter_v2_reset,"重置");
//		clickById(solo, R.id.activity_find_room_filter_v2_back, "确定");
//	}
//


	/**
	 * 封装截图函数
	 * 
	 * @param solo
	 * @return
	 */
	public static String screenShot(Solo solo) {
		System.out.println("截图开始------>");
		return ScreenShot.savePic(
				ScreenShot.takeScreenShot(solo.getCurrentActivity()),
				Report.getNowDateTime());
	}

	/**
	 * patheLineMemu点击某个按钮,除了点击主按钮以外，其他三个按钮点击的时候都要事先判断菜单是打开的还是关闭的
	 * 
	 * @param solo
	 * @param index
	 *            1表示附近，2表示搜索，3表示筛选，4表示>或者<
	 */

//	public static void pathLineMemu(Solo solo, int index) {
//		String message = "";
//		try {
//			Thread.sleep(500);
//			if (index == 4) {
//				message = "主按钮>或者<";
//				clickById(
//						solo,
//						solo.getViews(
//								solo.getView(R.id.activity_view_pathlinemenu_myown))
//								.get(index).getId(), message);
//			} else {
//				if (solo.getViews(solo.getView(R.id.activity_view_pathlinemenu_myown)).get(0).getWidth() != 362) {
//					clickById(solo,solo.getViews(solo.getView(R.id.activity_view_pathlinemenu_myown)).get(index).getId(), message);
//				}
//				switch (index) {
//				case 1:
//					message = "附近";
//					break;
//				case 2:
//					message = "搜索";
//					break;
//				case 3:
//					message = "筛选";
//					break;
//				default:
//					Report.writeHTMLLog("点击", "下标越界", "WARNING", "");
//					break;
//				}
//				clickById(solo,solo.getViews(solo.getView(R.id.activity_view_pathlinemenu_myown)).get(index).getId(), message);
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 等待控件出现
	 * 
	 * @param solo
	 * @param id
	 *            资源id
	 * @param count
	 *            总共等待秒数
	 * @param message
	 *            控件名字
	 * @return 出现就返回true
	 * @throws InterruptedException
	 */
	public static boolean wait(Solo solo, int id, int count, String message){
		int i = 1;
		String imag = "";
		while (solo.getView(id) == null) {
			if (i < count) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Report.writeHTMLLog("加载中...等待",	message + "：等待" + (i++) + " 次 ", "DONE", imag);
			} else {
				 imag =screenShot(solo);
				Report.writeHTMLLog("加载中...等待", "【"+ message +"】" + "无法显示", "FAIL", imag);
				return false;
			}
		}
		Report.writeHTMLLog("加载中完成", "【"+ message +"】" + " total wait " + (i - 1) + " 次",
				"DONE", "");
		return true;
	}
	
	/**
	 * 
	 * @param solo
	 * @param SCase
	 */
	public static void scroolUpPage(Solo solo,String SCase){
		boolean scroolFlag = true;
		while(scroolFlag){
			scroolFlag = solo.scrollUp();
			Report.writeHTMLLog(SCase, "scrool up", Report.DONE, "");
		}
		Report.writeHTMLLog(SCase, "已scroll到顶", Report.DONE, "");
 }
}
