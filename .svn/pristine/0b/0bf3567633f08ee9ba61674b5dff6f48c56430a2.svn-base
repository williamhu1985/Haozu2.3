// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Clicker.java

package com.jayway.android.robotium.solo;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper, RobotiumUtils, ViewFetcher, Waiter, 
//			Scroller

class Clicker
{

	private final String LOG_TAG = "Robotium";
	private final ViewFetcher viewFetcher;
	private final Scroller scroller;
	private final Instrumentation inst;
	private final RobotiumUtils robotiumUtils;
	private final Sleeper sleeper;
	private final Waiter waiter;
	private final int TIMEOUT = 10000;
	private final int MINISLEEP = 100;
	Set uniqueTextViews;

	public Clicker(ViewFetcher viewFetcher, Scroller scroller, RobotiumUtils robotiumUtils, Instrumentation inst, Sleeper sleeper, Waiter waiter)
	{
		this.viewFetcher = viewFetcher;
		this.scroller = scroller;
		this.robotiumUtils = robotiumUtils;
		this.inst = inst;
		this.sleeper = sleeper;
		this.waiter = waiter;
		uniqueTextViews = new HashSet();
	}

	public void clickOnScreen(float x, float y)
	{
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent event = MotionEvent.obtain(downTime, eventTime, 0, x, y, 0);
		MotionEvent event2 = MotionEvent.obtain(downTime, eventTime, 1, x, y, 0);
		try
		{
			inst.sendPointerSync(event);
			inst.sendPointerSync(event2);
			sleeper.sleep(100);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Click can not be completed!", false);
		}
	}

	public void clickLongOnScreen(float x, float y, int time)
	{
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent event = MotionEvent.obtain(downTime, eventTime, 0, x, y, 0);
		try
		{
			inst.sendPointerSync(event);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Click can not be completed! Something is in the way e.g. the keyboard.", false);
		}
		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, 2, x + (float)(ViewConfiguration.getTouchSlop() / 2), y + (float)(ViewConfiguration.getTouchSlop() / 2), 0);
		inst.sendPointerSync(event);
		if (time > 0)
			sleeper.sleep(time);
		else
			sleeper.sleep((int)((float)ViewConfiguration.getLongPressTimeout() * 2.5F));
		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, 1, x, y, 0);
		inst.sendPointerSync(event);
		sleeper.sleep();
	}

	public void clickOnScreen(View view)
	{
		clickOnScreen(view, false, 0);
	}

	public void clickOnScreen(View view, boolean longClick, int time)
	{
		if (view == null)
			Assert.assertTrue("View is null and can therefore not be clicked!", false);
		int xy[] = new int[2];
		view.getLocationOnScreen(xy);
		int viewWidth = view.getWidth();
		int viewHeight = view.getHeight();
		float x = (float)xy[0] + (float)viewWidth / 2.0F;
		float y = (float)xy[1] + (float)viewHeight / 2.0F;
		if (longClick)
			clickLongOnScreen(x, y, time);
		else
			clickOnScreen(x, y);
	}

	public void clickLongOnTextAndPress(String text, int index)
	{
		clickOnText(text, true, 0, true, 0);
		try
		{
			inst.sendKeyDownUpSync(20);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Can not press the context menu!", false);
		}
		for (int i = 0; i < index; i++)
		{
			sleeper.sleepMini();
			inst.sendKeyDownUpSync(20);
		}

		inst.sendKeyDownUpSync(66);
	}

	public void clickOnMenuItem(String text)
	{
		sleeper.sleep();
		try
		{
			robotiumUtils.sendKeyCode(82);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Can not open the menu!", false);
		}
		clickOnText(text, false, 1, true, 0);
	}

	public void clickOnMenuItem(String text, boolean subMenu)
	{
		sleeper.sleep();
		TextView textMore = null;
		int xy[] = new int[2];
		int x = 0;
		int y = 0;
		try
		{
			robotiumUtils.sendKeyCode(82);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Can not open the menu!", false);
		}
		if (subMenu && viewFetcher.getCurrentViews(android/widget/TextView).size() > 5 && !waiter.waitForText(text, 1, 1500L, false))
		{
			Iterator i$ = viewFetcher.getCurrentViews(android/widget/TextView).iterator();
			do
			{
				if (!i$.hasNext())
					break;
				TextView textView = (TextView)i$.next();
				x = xy[0];
				y = xy[1];
				textView.getLocationOnScreen(xy);
				if (xy[0] > x || xy[1] > y)
					textMore = textView;
			} while (true);
		}
		if (textMore != null)
			clickOnScreen(textMore);
		clickOnText(text, false, 1, true, 0);
	}

	public void clickOnText(String regex, boolean longClick, int match, boolean scroll, int time)
	{
		waiter.waitForText(regex, 0, 10000L, scroll, true);
		TextView textToClick = null;
		ArrayList allTextViews = viewFetcher.getCurrentViews(android/widget/TextView);
		allTextViews = RobotiumUtils.removeInvisibleViews(allTextViews);
		if (match == 0)
			match = 1;
		Iterator i$ = allTextViews.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			TextView textView = (TextView)i$.next();
			if (RobotiumUtils.checkAndGetMatches(regex, textView, uniqueTextViews) != match)
				continue;
			uniqueTextViews.clear();
			textToClick = textView;
			break;
		} while (true);
		if (textToClick != null)
			clickOnScreen(textToClick, longClick, time);
		else
		if (scroll && scroller.scroll(0))
		{
			clickOnText(regex, longClick, match, scroll, time);
		} else
		{
			int sizeOfUniqueTextViews = uniqueTextViews.size();
			uniqueTextViews.clear();
			if (sizeOfUniqueTextViews > 0)
			{
				Assert.assertTrue((new StringBuilder()).append("There are only ").append(sizeOfUniqueTextViews).append(" matches of ").append(regex).toString(), false);
			} else
			{
				TextView textView;
				for (Iterator i$ = allTextViews.iterator(); i$.hasNext(); Log.d("Robotium", (new StringBuilder()).append(regex).append(" not found. Have found: ").append(textView.getText()).toString()))
					textView = (TextView)i$.next();

				Assert.assertTrue((new StringBuilder()).append("The text: ").append(regex).append(" is not found!").toString(), false);
			}
		}
	}

	public void clickOn(Class viewClass, String nameRegex)
	{
		Pattern pattern = Pattern.compile(nameRegex);
		waiter.waitForText(nameRegex, 0, 10000L, true, true);
		ArrayList views = viewFetcher.getCurrentViews(viewClass);
		views = RobotiumUtils.removeInvisibleViews(views);
		TextView viewToClick = null;
		Iterator i$ = views.iterator();
label0:
		do
		{
			TextView view;
			do
			{
				if (!i$.hasNext())
					break label0;
				view = (TextView)i$.next();
			} while (!pattern.matcher(view.getText().toString()).matches());
			viewToClick = view;
		} while (!viewToClick.isShown());
		if (viewToClick != null)
			clickOnScreen(viewToClick);
		else
		if (scroller.scroll(0))
		{
			clickOn(viewClass, nameRegex);
		} else
		{
			TextView view;
			for (i$ = views.iterator(); i$.hasNext(); Log.d("Robotium", (new StringBuilder()).append(nameRegex).append(" not found. Have found: ").append(view.getText()).toString()))
				view = (TextView)i$.next();

			Assert.assertTrue((new StringBuilder()).append(viewClass.getSimpleName()).append(" with the text: ").append(nameRegex).append(" is not found!").toString(), false);
		}
	}

	public void clickOn(Class viewClass, int index)
	{
		clickOnScreen(waiter.waitForAndGetView(index, viewClass));
	}

	public ArrayList clickInList(int line)
	{
		return clickInList(line, 0, false, 0);
	}

	public ArrayList clickInList(int line, int index, boolean longClick, int time)
	{
		if (--line < 0)
			line = 0;
		ArrayList views = new ArrayList();
		ListView listView = (ListView)waiter.waitForAndGetView(index, android/widget/ListView);
		if (listView == null)
			Assert.assertTrue("ListView is null!", false);
		View view = listView.getChildAt(line);
		if (view != null)
		{
			views = viewFetcher.getViews(view, true);
			views = RobotiumUtils.removeInvisibleViews(views);
			clickOnScreen(view, longClick, time);
		}
		return RobotiumUtils.filterViews(android/widget/TextView, views);
	}
}
