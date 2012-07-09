package com.anjuke.android.haozu.test;

import java.util.ArrayList;

import com.anjuke.android.haozu.R;
import com.anjuke.android.haozu.activity.WelcomeActivity;
import com.anjuke.android.haozu.function.Action;
import com.anjuke.android.util.Report;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


/*
 * @author:William
 * 测试用例步骤简介：
 * #1.在附近页房源列表中点击第一套房源并进入房源详细页，获取房源标题，并点击收藏按钮
 * #2.点击主菜单中的“我的”按钮，并进入收藏房源列表，选取第一套并再次进入房源详细页获取标题
 * #3.比较两次获得的标题是否一致，预期一致
 * @CreateDate: 2012/05/28
 */

public class CollectHouseTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {
	private Solo solo;
	private boolean flag;
	private int count = 3;
	private String testName = "好租收藏房源测试用例";

    
	public CollectHouseTest(){
		super("com.anjuke.haozu",WelcomeActivity.class);
	}
	
	@Override
	public void setUp() throws Exception{
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	@Override
	public void tearDown(){
		solo.finishOpenedActivities();
	}
	
	public void testCollectHouse(){
		try {
			Report.setTCNameLog(testName);			
			//点击“附近”tab页第一套房源，默认是在附近tab页
			if(solo.searchText("下拉刷新再试试")){
				solo.clickOnText("下来刷新再试试");
			}
			solo.waitForView(solo.getView(R.id.title));
			Action.scroolUpPage(solo, "收藏房源");
			solo.clickOnView(solo.getView(R.id.title));
			System.out.println("1---->");
			solo.waitForView(solo.getView(R.id.prop_title));
			System.out.println("2---->");
			
			//获取详细页中的房源标题
			String propTitle = (String)((TextView)solo.getView(R.id.prop_title)).getText();        
			Report.writeHTMLLog("获取房源列表第一套房源标题", "房源列表第一套房源名字为"+propTitle, Report.DONE, "");
			System.out.println("成功获取第一套房源标题---->"+propTitle);
			//如果当前房源是“取消收藏”状态，则先点击“取消收藏”按钮
			Action.wait(solo, R.id.btfavorite, count, "等待收藏房源按钮完全显示");
			if(((String)((TextView)solo.getView(R.id.btfavorite)).getText()).equals("取消收藏")){
				solo.clickOnView(solo.getView(R.id.btfavorite));
			}
			
			//点击收藏房源按钮
			solo.clickOnView(solo.getView(R.id.btfavorite));
			//点击返回按钮
			solo.clickOnButton(0);
			//点击“我的”选项按钮
			solo.clickOnText("我的");
			
			//进入后默认是“我的收藏”选项
			solo.waitForText("编辑");			
			solo.clickOnView(solo.getView(R.id.title));			
			Action.wait(solo, R.id.prop_title, count, "等待房源详细页面加载成功");

			String myFavPropTitle = (String)((TextView)solo.getView(R.id.prop_title)).getText();
			Report.writeHTMLLog("获取收藏房源列表第一套房源标题", "收藏房源列表中第一套房源的标题为："+ myFavPropTitle, Report.PASS, "");			
			Action.assertString(solo, "比较选取的房源列表第一个的标题与收藏房源列表第一个的标题是否一致", propTitle, myFavPropTitle);
			Report.writeHTMLLog("收藏房源测试用例", "收藏房源成功", Report.PASS, "");
//			Report.seleniumReport();
		} catch (Exception e) {
            String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}	
  }
    
//	public static CollectHouseTest returnClass(Solo solo){
//		return new CollectHouseTest(solo);
//	}
//   
}
	

