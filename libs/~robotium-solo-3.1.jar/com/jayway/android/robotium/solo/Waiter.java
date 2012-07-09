// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Waiter.java

package com.jayway.android.robotium.solo;

import android.view.View;
import android.widget.TextView;
import java.util.*;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			ActivityUtils, Sleeper, Searcher, Scroller, 
//			ViewFetcher, RobotiumUtils

class Waiter
{

	private final ActivityUtils activityUtils;
	private final ViewFetcher viewFetcher;
	private final int TIMEOUT = 20000;
	private final int SMALLTIMEOUT = 10000;
	private final Searcher searcher;
	private final Scroller scroller;
	private final Sleeper sleeper;

	public Waiter(ActivityUtils activityUtils, ViewFetcher viewFetcher, Searcher searcher, Scroller scroller, Sleeper sleeper)
	{
		this.activityUtils = activityUtils;
		this.viewFetcher = viewFetcher;
		this.searcher = searcher;
		this.scroller = scroller;
		this.sleeper = sleeper;
	}

	public boolean waitForActivity(String name)
	{
		return waitForActivity(name, 10000);
	}

	public boolean waitForActivity(String name, int timeout)
	{
		long now = System.currentTimeMillis();
		long endTime;
		for (endTime = now + (long)timeout; !activityUtils.getCurrentActivity().getClass().getSimpleName().equals(name) && now < endTime; now = System.currentTimeMillis());
		return now < endTime;
	}

	public boolean waitForView(Class viewClass, int index, boolean sleep, boolean scroll)
	{
		java.util.Set uniqueViews = new HashSet();
		do
		{
			if (sleep)
				sleeper.sleep();
			boolean foundMatchingView = searcher.searchFor(uniqueViews, viewClass, index);
			if (foundMatchingView)
				return true;
			if (scroll && !scroller.scroll(0))
				return false;
		} while (scroll);
		return false;
	}

	public boolean waitForView(Class viewClass, int index, int timeout, boolean scroll)
	{
		java.util.Set uniqueViews = new HashSet();
		long endTime = System.currentTimeMillis() + (long)timeout;
		do
		{
			if (System.currentTimeMillis() >= endTime)
				break;
			sleeper.sleep();
			boolean foundMatchingView = searcher.searchFor(uniqueViews, viewClass, index);
			if (foundMatchingView)
				return true;
			if (scroll)
				scroller.scroll(0);
		} while (true);
		return false;
	}

	public boolean waitForViews(Class viewClass, Class viewClass2)
	{
		for (long endTime = System.currentTimeMillis() + 10000L; System.currentTimeMillis() < endTime; sleeper.sleep())
		{
			if (waitForView(viewClass, 0, false, false))
				return true;
			if (waitForView(viewClass2, 0, false, false))
				return true;
			scroller.scroll(0);
		}

		return false;
	}

	public boolean waitForView(View view)
	{
		return waitForView(view, 20000, true);
	}

	public boolean waitForView(View view, int timeout)
	{
		return waitForView(view, timeout, true);
	}

	public boolean waitForView(View view, int timeout, boolean scroll)
	{
		long startTime = System.currentTimeMillis();
		long endTime = startTime + (long)timeout;
		do
		{
			if (System.currentTimeMillis() >= endTime)
				break;
			sleeper.sleep();
			boolean foundAnyMatchingView = searcher.searchFor(view);
			if (foundAnyMatchingView)
				return true;
			if (scroll)
				scroller.scroll(0);
		} while (true);
		return false;
	}

	public View waitForView(int id)
	{
		ArrayList views = new ArrayList();
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 10000L;
		View v;
label0:
		do
			if (System.currentTimeMillis() <= endTime)
			{
				sleeper.sleep();
				views = viewFetcher.getAllViews(false);
				Iterator i$ = views.iterator();
				do
				{
					if (!i$.hasNext())
						continue label0;
					v = (View)i$.next();
				} while (v.getId() != id);
				break;
			} else
			{
				return null;
			}
		while (true);
		views = null;
		return v;
	}

	public boolean waitForText(String text)
	{
		return waitForText(text, 0, 20000L, true);
	}

	public boolean waitForText(String text, int expectedMinimumNumberOfMatches)
	{
		return waitForText(text, expectedMinimumNumberOfMatches, 20000L, true);
	}

	public boolean waitForText(String text, int expectedMinimumNumberOfMatches, long timeout)
	{
		return waitForText(text, expectedMinimumNumberOfMatches, timeout, true);
	}

	public boolean waitForText(String text, int expectedMinimumNumberOfMatches, long timeout, boolean scroll)
	{
		return waitForText(text, expectedMinimumNumberOfMatches, timeout, scroll, false);
	}

	public boolean waitForText(String text, int expectedMinimumNumberOfMatches, long timeout, boolean scroll, boolean onlyVisible)
	{
		long endTime = System.currentTimeMillis() + timeout;
		boolean foundAnyTextView;
		do
		{
			boolean timedOut = System.currentTimeMillis() > endTime;
			if (timedOut)
				return false;
			sleeper.sleep();
			foundAnyTextView = searcher.searchFor(android/widget/TextView, text, expectedMinimumNumberOfMatches, scroll, onlyVisible);
		} while (!foundAnyTextView);
		return true;
	}

	public View waitForAndGetView(int index, Class classToFilterBy)
	{
		for (long endTime = System.currentTimeMillis() + 10000L; System.currentTimeMillis() <= endTime && !waitForView(classToFilterBy, index, true, true););
		int numberOfUniqueViews = searcher.getNumberOfUniqueViews();
		ArrayList views = RobotiumUtils.removeInvisibleViews(viewFetcher.getCurrentViews(classToFilterBy));
		if (views.size() < numberOfUniqueViews)
		{
			int newIndex = index - (numberOfUniqueViews - views.size());
			if (newIndex >= 0)
				index = newIndex;
		}
		View view = null;
		try
		{
			view = (View)views.get(index);
		}
		catch (IndexOutOfBoundsException exception)
		{
			Assert.assertTrue((new StringBuilder()).append(classToFilterBy.getSimpleName()).append(" with index ").append(index).append(" is not available!").toString(), false);
		}
		views = null;
		return view;
	}
}
