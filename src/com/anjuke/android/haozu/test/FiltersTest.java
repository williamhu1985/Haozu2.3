package com.anjuke.android.haozu.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.anjuke.android.haozu.R;
import com.anjuke.android.haozu.activity.WelcomeActivity;
import com.anjuke.android.haozu.function.Action;
import com.anjuke.android.util.Report;
import com.jayway.android.robotium.solo.Solo;

/*
 * @author:WilliamHu
 * #1.进入haozu客户端，默认在”附近“tab页
 * #2.Case1：按关键字进行搜索
 * #3.Case2：按区域进行搜索
 * #4.Case3: 按地铁站点进行搜索
 * #5.验证搜索信息是否与搜索条件一致
 */



public class FiltersTest extends ActivityInstrumentationTestCase2<WelcomeActivity>{
	private Solo solo;
	private String testName = "好租筛选过滤测试用例";
    private String bureau = "浦东";
    private String area = "金桥";
    private String metroLine = "6号线";
    private String metroStation = "世纪大道";
    private String searchTip = "";
    
	public FiltersTest(){
		super("com.anjuke.haozu",WelcomeActivity.class);		
	}

	@Override
	public void setUp(){
		solo = new Solo(getInstrumentation(),getActivity());
	}

	@Override
	public void tearDown(){
		solo.finishOpenedActivities();
	}

	public void testFilters(){
		try {
			Report.setTCNameLog(testName);

			//case1:搜索页按选择区域搜索
			//点击“搜索”View
			solo.waitForView(solo.getView(R.id.title));
			solo.clickOnText("搜索");
			Report.writeHTMLLog("按区域搜索", "点击搜索按钮", Report.DONE, "");
			
			//点击切换城市按钮  
			solo.clickOnButton("切换城市");
			solo.waitForText("返回");
			solo.clickOnText("上海");
			Report.writeHTMLLog("按区域搜索", "切换城市至上海", Report.DONE, "");		

			//选择”按区域板块”搜索 
			solo.clickOnText("按区域板块");
			//选中浦东->金桥 
			solo.clickOnText(bureau);
			solo.clickOnText(area);
			
			//获取搜索栏信息
			searchTip =  (String)((TextView)solo.getView(R.id.activity22_search_list_searchtip)).getText();
			Action.assertFilter(solo, "筛选条件和筛选信息栏",searchTip,area);
			Report.writeHTMLLog("搜索验证", "按区域板块验证成功", Report.PASS, "");
			
			//Case2:搜索页输入关键字		
			//点击搜索按钮		
			solo.clickOnText("搜索");
			solo.clickOnView(solo.getView(R.id.activity_search_address_et));
			solo.enterText(0, bureau.concat(area));
			solo.clickOnButton(0);	
			if(solo.searchText("可能的小区")){
				solo.clickOnText("融都金桥园");
			}                					
			//获取搜索栏信息
			searchTip =  (String)((TextView)solo.getView(R.id.activity22_search_list_searchtip)).getText();
			Action.assertFilter(solo, "筛选条件和筛选信息栏",searchTip,bureau.concat(area));
			Report.writeHTMLLog("搜索验证", "按关键字验证成功", Report.PASS, "");
			
			//case3:搜索页按地铁站点搜索			
			//点击搜索按钮
			solo.clickOnText("搜索");
			//选择”按区域板块”搜索
			solo.clickOnText("按地铁站点");		
			solo.clickOnText(metroLine);
			solo.clickOnText(metroStation);
			
			//获取搜索栏信息
			searchTip =  (String)((TextView)solo.getView(R.id.activity22_search_list_searchtip)).getText();
			Action.assertFilter(solo, "筛选条件和筛选信息栏",searchTip,metroLine,metroStation);
			Report.writeHTMLLog("搜索验证", "按地铁站点验证成功", Report.PASS, "");
			Report.writeHTMLLog("搜索验证", "搜索验证成功", Report.PASS, "");
//			Report.seleniumReport();
		} catch (Exception e) {
			String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}
	}
}