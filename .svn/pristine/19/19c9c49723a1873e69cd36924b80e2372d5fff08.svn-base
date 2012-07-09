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
 * #1.在附近页房源列表中点击第一套房源并进入房源详细页，获取房源标题
 * #2.点击主菜单中的“我的”按钮，并进入“最近浏览”tab页面，选取第一套并再次进入房源详细页获取标题
 * #3.比较两次获得的标题是否一致，预期一致
 * @CreateDate: 2012/05/28
 */

public class BrowseHistoryHouseTitleTest extends ActivityInstrumentationTestCase2<WelcomeActivity>{
	private Solo solo;
	private  int count = 3;
	private String testName = "好租浏览房源测试用例";
    private String bureau = "浦东";
    private String area = "金桥";
    
	
	public BrowseHistoryHouseTitleTest(){
		super("com.anjuke.android.haozu",WelcomeActivity.class);
//		this.solo = solo;
	}
	
	@Override
	public void setUp(){
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	
	@Override
	public void tearDown(){
	   solo.finishOpenedActivities();	
	}
	
	
	public void testBrowseHistoryHouseTitle(){
		try {
			Report.setTCNameLog(testName);		

			//点击“附近”tab页第一套房源
			solo.waitForView(solo.getView(R.id.title));
			Action.scroolUpPage(solo, "浏览历史房源");
			solo.clickOnView(solo.getView(R.id.title));			
			Action.wait(solo, R.id.prop_title, count, "等待房源详细页面加载成功");
			
			String houseTitle = (String)((TextView)solo.getCurrentActivity().findViewById(R.id.prop_title)).getText();
			System.out.println("选取到的房源标题为"+ houseTitle + "---->");
			
			solo.clickOnButton(0);					
			solo.clickOnText("我的");
			solo.clickOnText("最近浏览");
			
			solo.waitForText("清空");
			solo.clickOnView(solo.getView(R.id.title));
			Action.wait(solo, R.id.prop_title, count, "等待房源详细页面加载成功");

			String browseFirstTitle = (String)((TextView)solo.getCurrentActivity().findViewById(R.id.prop_title)).getText();
			System.out.println("筛选结果列表中第一条房源标题为:" + browseFirstTitle + "---->");
			Report.writeHTMLLog("获取最近浏览列表第一套房源名字", "最近浏览列表第一套房源名字："
					+ browseFirstTitle, Report.DONE, ""); 
     
			Action.assertString(solo, "房源列表第一套的名字与最近浏览列表第一套的名字比较", houseTitle, browseFirstTitle);			
			Report.writeHTMLLog("最近浏览房源标题比较", "房源标题匹配成功", Report.PASS, "");
//			Report.seleniumReport();
		} catch (Exception e) {
            String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}
	}
	
//	public static BrowseHistoryHouseTitleTest returnClass(Solo solo){
//		return new BrowseHistoryHouseTitleTest(solo);
//	}
	
}