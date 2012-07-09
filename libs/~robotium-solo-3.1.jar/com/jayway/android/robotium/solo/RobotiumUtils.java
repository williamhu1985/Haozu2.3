// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RobotiumUtils.java

package com.jayway.android.robotium.solo;

import android.app.Instrumentation;
import android.view.View;
import android.widget.TextView;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper

class RobotiumUtils
{

	private final Instrumentation inst;
	private final Sleeper sleeper;

	public RobotiumUtils(Instrumentation inst, Sleeper sleeper)
	{
		this.inst = inst;
		this.sleeper = sleeper;
	}

	public void goBack()
	{
		sleeper.sleep();
		try
		{
			inst.sendKeyDownUpSync(4);
			sleeper.sleep();
		}
		catch (Throwable e) { }
	}

	public void sendKeyCode(int keycode)
	{
		sleeper.sleep();
		try
		{
			inst.sendCharacterSync(keycode);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Can not complete action!", false);
		}
	}

	public static ArrayList removeInvisibleViews(ArrayList viewList)
	{
		ArrayList tmpViewList = new ArrayList(viewList.size());
		Iterator i$ = viewList.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			View view = (View)i$.next();
			if (view != null && view.isShown())
				tmpViewList.add(view);
		} while (true);
		return tmpViewList;
	}

	public static ArrayList filterViews(Class classToFilterBy, ArrayList viewList)
	{
		ArrayList filteredViews = new ArrayList(viewList.size());
		Iterator i$ = viewList.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			View view = (View)i$.next();
			if (view != null && classToFilterBy.isAssignableFrom(view.getClass()))
				filteredViews.add(classToFilterBy.cast(view));
		} while (true);
		viewList = null;
		return filteredViews;
	}

	public static int checkAndGetMatches(String regex, TextView view, Set uniqueTextViews)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(view.getText().toString());
		if (matcher.find())
			uniqueTextViews.add(view);
		if (view.getError() != null)
		{
			matcher = pattern.matcher(view.getError().toString());
			if (matcher.find())
				uniqueTextViews.add(view);
		}
		if (view.getText().toString().equals("") && view.getHint() != null)
		{
			matcher = pattern.matcher(view.getHint().toString());
			if (matcher.find())
				uniqueTextViews.add(view);
		}
		return uniqueTextViews.size();
	}
}
