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
 * @author:WilliamHu
 * #1.打开haozu客户端,默认在"附近"tab页，点击"筛选"按钮
 * #2.设置完筛选条件并保存
 * #3.选中第一套筛选房源并进入详细信息页面
 * #4.比较房源信息与筛选条件是否符合
 */


public class NearBySearchConditionFilterTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {
	private Solo solo;
	boolean flag;
	private String testName = "好租附近页筛选条件测试用例";
   
	public NearBySearchConditionFilterTest(){
		super("com.anjuke.haozu",WelcomeActivity.class);
	}
	
	@Override
	public void setUp() throws Exception{
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	@Override
	public void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}
	
	public void testSearchConditionFilter(){		
		try {
			Report.setTCNameLog(testName);	
			solo.clickOnText("筛选");		
			solo.clickOnText("重置");
			String price = Action.clickInFilter(solo, 5, "租金");
			
			//过滤价格里的空格
			if(price.indexOf("-")>=0){
				price=price.substring(0, price.indexOf("-")-1)+"-"+price.substring(price.indexOf("-")+2,price.length());
			}
			String type = Action.clickInFilter(solo, 2, "户型");
			String source = Action.clickInFilter(solo, 2, "来源");
			String rentType= Action.clickInFilter(solo, 3, "方式");
			Action.clickById(solo, R.id.activity_find_room_filter_v2_back, "确定");
			
			Action.wait(solo, R.id.activity22_nearby_list_searchtip, 5, "等待筛选结果页面加载成功");
			
			//获取搜索栏信息
			solo.waitForView(solo.getView(R.id.title));  //等待直到第一套房源显示完全再取searchTip
			String searchTip =  (String)((TextView)solo.getView(R.id.activity22_nearby_list_searchtip)).getText();
			Action.assertFilter(solo, "筛选条件和筛选信息栏", searchTip,price, type, source, rentType);

			//等待房源列表加载完成并点击
			flag=Action.wait(solo, R.id.title, 5, "房源列表");
			if(flag==false){
				 return;
			 }
			Action.clickById(solo, R.id.title, "房源列表第一套房源");
			
			//获取房源详情中的价格，房型，租赁方式和来源
			Action.wait(solo, R.id.prop_title, 5, "房源详情");
			String propPrice = (String) ((TextView) solo.getView(R.id.prop_total_price)).getText();
			String propType = (String) ((TextView) solo.getView(R.id.prop_type)).getText();
			String propRentType = (String) ((TextView) solo.getView(R.id.prop_rent_type)).getText();
			String propSource = (String) ((TextView) solo.getView(R.id.broker_name)).getText();
			Report.writeHTMLLog("获取房源详情", "房源详情："+propSource +" "+ propType + " "+propPrice + propRentType,"DONE", Action.screenShot(solo));

			//对价格，房型，租赁方式和来源进行字符串处理
			//价格
			int intercept = propPrice.indexOf("元");
			propPrice = propPrice.substring(0, intercept);
			//房型
			intercept= propType.indexOf("室");
			propType = propType.substring(0, intercept);
			if (propType.equals("1")) {
				propType = "一室";
			} else if (propType.equals("2")) {
				propType = "二室";
			} else if (propType.equals("3")) {
				propType = "三室";
			} else if (propType.equals("4")) {
				propType = "四室";
			} else {
				propType = "五室及以上";
			}
			//租赁方式
			propRentType = propRentType.substring(1, 3);
			//来源
			if (propSource.indexOf("经纪人") >= 0) {
				propSource = "置业顾问";
			} else {
				propSource = "个人";
			}
			System.out.println("tttttt:" + propPrice + propRentType
					+ propSource + propType);
			//比较房子实际价格是否在筛选价格区间内
			assertPrice(price, propPrice);
			//比较户型，来源和租赁方式
			assertString("户型",type, propType);
			assertString("来源", source, propSource);
			assertString("租赁方式", rentType, propRentType);
			Report.writeHTMLLog("附近房源筛选", "附近房源筛选用例成功", Report.PASS, "");
			
//			Report.seleniumReport();
		} catch (Exception e) {
			String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}		
	}
	
	
	/**
	 * 比较价格，price参数的格式必须是xxx-xxx元，中间不能有其他字符包括空格
	 * @param price   	选择的价格
	 * @param propPrice	房子实际的价格
	 */
	
	public void assertPrice(String price,String propPrice){
		int minPrice=0;
		int maxPrice=0;
		int index=0;
		if(price.equals("不限")){
			Report.writeHTMLLog("比较", "价格不限,value="+propPrice, "PASS", "");
		}else if(price.indexOf("以上")>=0){
			index=price.indexOf("元");
			price = price.substring(0, index);
			minPrice = Integer.parseInt(price);
			if(Integer.parseInt(propPrice)>=minPrice){
				Report.writeHTMLLog("比较", "价格一致,value="+propPrice, "PASS", "");
			}else{
				Report.writeHTMLLog("比较", "价格不一致,expect="+price+"以上,receive="+propPrice, "FAIL", "");
			}
		}else if(price.indexOf("以下")>=0){
			index=price.indexOf("元");
			price = price.substring(0, index);
			maxPrice = Integer.parseInt(price);
			if(Integer.parseInt(propPrice)<=maxPrice){
				Report.writeHTMLLog("比较", "价格一致，value="+propPrice, "PASS", "");
			}else{
				Report.writeHTMLLog("比较", "价格不一致,expect="+price+"以下,receive="+propPrice, "FAIL", "");
			}
		}else{
			index = price.indexOf("-");
			minPrice = Integer.parseInt(price.substring(0,index));
			maxPrice = Integer.parseInt(price.substring(index+1, price.length()-1));
			if(Integer.parseInt(propPrice)>=minPrice&&Integer.parseInt(propPrice)<=maxPrice){
				Report.writeHTMLLog("比较", "价格一致,value="+propPrice, "PASS", "");
			}else{
				Report.writeHTMLLog("比较", "价格不一致,expect="+minPrice+"-"+maxPrice+"元，receive="+propPrice, "FAIL", "");
			}
		}
	}
	/**
	 * 专门用来判断筛选条件为“不限”和其他条件的函数
	 * @param message 	比较的内容
	 * @param expect	筛选选择的值
	 * @param receive	实际房子的值
	 */
	public void assertString(String message,String expect,String receive){
		if(expect.equals("不限")==false){
			Action.assertString(solo, message, expect, receive);
		}else{
			Report.writeHTMLLog("比较", message, "PASS", "");
		}
	}
	
}
	

