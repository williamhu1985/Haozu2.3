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
import static junit.framework.Assert.*;


/*
 * @author:William
 * 测试用例步骤简介：
 * #1.设置完找房筛选条件
 * #2.进入列表页，再次进入筛选页面
 * #3.查看当前默认筛选条件是否与上次设置的一致
 * @CreateDate: 2012/05/25
 */

public class SearchFilterConfirmTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {
	private Solo solo;
	boolean flag;
	private int count = 3;
	private String testName = "好租搜索页筛选条件筛选确认测试用例";

	public SearchFilterConfirmTest(){
		super("com.anjuke.android.haozu",WelcomeActivity.class);
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
			//点击“搜索”View
			solo.clickOnText("搜索");
			//点击切换城市按钮
			solo.clickOnButton("切换城市");
			solo.clickOnText("上海");
			//选择”按区域板块”搜索
			solo.clickOnText("按区域板块");
			solo.waitForText("返回");
			Report.writeHTMLLog("好租V2.2搜索页筛选条件筛选确认测试用例", "选则区域页面成功显示", Report.PASS, "");

			//选中浦东->金桥
			solo.clickOnText("浦东");
			solo.clickOnText("金桥");
			solo.clickOnView(solo
					.getView(R.id.activity22_search_list_btn_filter));
			solo.clickOnText("重置");
			//设置筛选找房条件
			Action.selectFilterCondition(solo, 5, 2, 2, 3);
			//再一次点击筛选按钮
			solo.clickOnView(solo
					.getView(R.id.activity22_search_list_btn_filter));
			//判断当前显示的筛选条件与上面选取的相同		
			Boolean flag = Action.checkFilterCondition(solo, 5, 2, 2, 3);
			if(flag){
				Report.writeHTMLLog("好租搜索页筛选条件测试用例",
						"筛选条件匹配成功，是预期结果,用例顺利执行", Report.PASS, "");
			} else {
				String ps = Action.screenShot(solo);
				Report.writeHTMLLog("好租搜索页筛选条件测试用例",
						"筛选条件匹配不成功，不是预期结果,用例执行失败", Report.FAIL, ps);
			}
//			Report.seleniumReport();
		} catch (Exception e) {
			String ps = Action.screenShot(solo);
			Report.writeHTMLLog("出现异常:"+ e.getMessage(), "脚本停止", Report.FAIL, ps);
//			Report.seleniumReport();
		}		
	}

}	

	

