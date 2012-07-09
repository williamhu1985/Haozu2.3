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

public class SendReportTest extends ActivityInstrumentationTestCase2<WelcomeActivity>{


	public SendReportTest(){
		super("com.anjuke.android.haozu",WelcomeActivity.class);
	}
	
	@Override
	public void setUp(){
//		solo = new Solo(getInstrumentation(),getActivity());
	}

	@Override
	public void tearDown(){
//		solo.finishOpenedActivities();	
	}

	public void testBrowseHistoryHouseTitle(){
           Report.seleniumReport();
	}

}