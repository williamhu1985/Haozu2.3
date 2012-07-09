package com.anjuke.android.haozu.test;


import com.anjuke.android.haozu.activity.WelcomeActivity;
import com.anjuke.android.haozu.function.Action;
import com.jayway.android.robotium.solo.Solo;
import com.anjuke.android.haozu.R;
import com.anjuke.android.util.Report;
import com.anjuke.android.util.ScreenShot;


import android.test.ActivityInstrumentationTestCase2;

import android.widget.TextView;

/*
 * @author:William
 * 测试用例步骤简介：
 * #1.在搜索页面切换城市（非上海）
 * #2.点击附近tab按钮，获取页面上方的定位信息
 * #3.判断当前的定位仍然在上海
 * @CreateDate: 2012/05/28
 */

public class SwitchCityTest extends ActivityInstrumentationTestCase2<WelcomeActivity>{
	private Solo solo;
	private String testName = "好租切换城市测试";
    private String address;

	public SwitchCityTest(){
		super("com.anjuke.android.haozu",WelcomeActivity.class);
	}
	
	@Override
	public void setUp(){
		solo = new Solo(getInstrumentation(),getActivity());
	}

	@Override
	public void tearDown(){
		solo.finishOpenedActivities();	
	}

	public void testSwitchCityTitle(){
		try {
			Report.setTCNameLog(testName);		
			//点击“搜索”View
			solo.clickOnText("搜索");
//			solo.waitForView(solo.getView(R.id.title));
			Report.writeHTMLLog("切换城市地址验证", "点击搜索tab页", Report.DONE,"");

			//点击切换城市按钮
			solo.clickOnButton("切换城市");				
			solo.clickOnText("北京");
			Report.writeHTMLLog("切换城市地址验证", "切换至北京", Report.DONE,"");
			
			//点击“附近”标签页
			solo.clickOnText("附近");
			Report.writeHTMLLog("切换城市地址验证", "点击附近tab", Report.DONE,"");
			
			//等待附近列表中的第一套房源显示出来，此时表示已定位成功
			solo.waitForView(solo.getView(R.id.title));
			address = (String)((TextView)solo.getView(R.id.activity22_nearby_list_searchtip)).getText();
            Report.writeHTMLLog("切换城市地址验证", "获取当前GPS定位到的地址为："+ address, Report.DONE, "");
            if(address.contains("浦东新区")||address.equals("明珠塔路")||address.contains("陆家嘴")){
            	Report.writeHTMLLog("切换城市地址验证", "当前定位到的地址仍然在上海，测试正确", Report.PASS, "");
            }else{
            	String ps = Action.screenShot(solo);
            	Report.writeHTMLLog("切换城市地址验证", "当前定位到的地址已经不在上海，测试失败", Report.FAIL, ps);            	
            }
            Report.writeHTMLLog("切换城市地址验证", "切换城市用例测试通过", Report.PASS, "");
//			Report.seleniumReport();
		} catch (Exception e) {
			String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}
	}

}