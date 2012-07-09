// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Searcher.java

package com.jayway.android.robotium.solo;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.*;
import java.util.concurrent.Callable;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper, ViewFetcher, RobotiumUtils, Scroller

class Searcher
{

	private final ViewFetcher viewFetcher;
	private final Scroller scroller;
	private final Sleeper sleeper;
	private final int TIMEOUT = 5000;
	private final String LOG_TAG = "Robotium";
	Set uniqueTextViews;
	private int numberOfUniqueViews;

	public Searcher(ViewFetcher viewFetcher, Scroller scroller, Sleeper sleeper)
	{
		this.viewFetcher = viewFetcher;
		this.scroller = scroller;
		this.sleeper = sleeper;
		uniqueTextViews = new HashSet();
	}

	public boolean searchWithTimeoutFor(Class viewClass, String regex, int expectedMinimumNumberOfMatches, boolean scroll, boolean onlyVisible)
	{
		for (long endTime = System.currentTimeMillis() + 5000L; System.currentTimeMillis() < endTime;)
		{
			sleeper.sleep();
			boolean foundAnyMatchingView = searchFor(viewClass, regex, expectedMinimumNumberOfMatches, scroll, onlyVisible);
			if (foundAnyMatchingView)
				return true;
		}

		return false;
	}

	public boolean searchFor(final Class viewClass, String regex, int expectedMinimumNumberOfMatches, boolean scroll, final boolean onlyVisible)
	{
		Callable viewFetcherCallback = new Callable() {

			final boolean val$onlyVisible;
			final Class val$viewClass;
			final Searcher this$0;

			public Collection call()
				throws Exception
			{
				sleeper.sleep();
				if (onlyVisible)
					return RobotiumUtils.removeInvisibleViews(viewFetcher.getCurrentViews(viewClass));
				else
					return viewFetcher.getCurrentViews(viewClass);
			}

			public volatile Object call()
				throws Exception
			{
				return call();
			}

			
			{
				this$0 = Searcher.this;
				onlyVisible = flag;
				viewClass = class1;
				super();
			}
		};
		return searchFor(viewFetcherCallback, regex, expectedMinimumNumberOfMatches, scroll);
		Exception e;
		e;
		throw new RuntimeException(e);
	}

	public boolean searchFor(Set uniqueViews, Class viewClass, int index)
	{
		ArrayList allViews = RobotiumUtils.removeInvisibleViews(viewFetcher.getCurrentViews(viewClass));
		int uniqueViewsFound = getNumberOfUniqueViews(uniqueViews, allViews);
		if (uniqueViewsFound > 0 && index < uniqueViewsFound)
			return setArrayToNullAndReturn(true, allViews);
		if (uniqueViewsFound > 0 && index == 0)
			return setArrayToNullAndReturn(true, allViews);
		else
			return setArrayToNullAndReturn(false, allViews);
	}

	private boolean setArrayToNullAndReturn(boolean booleanToReturn, ArrayList views)
	{
		views = null;
		return booleanToReturn;
	}

	public boolean searchFor(View view)
	{
		ArrayList views = viewFetcher.getAllViews(true);
		for (Iterator i$ = views.iterator(); i$.hasNext();)
		{
			View v = (View)i$.next();
			if (v.equals(view))
				return true;
		}

		return false;
	}

	public boolean searchFor(Callable viewFetcherCallback, String regex, int expectedMinimumNumberOfMatches, boolean scroll)
		throws Exception
	{
		if (expectedMinimumNumberOfMatches < 1)
			expectedMinimumNumberOfMatches = 1;
		do
		{
			Collection views = (Collection)viewFetcherCallback.call();
			for (Iterator i$ = views.iterator(); i$.hasNext();)
			{
				TextView view = (TextView)i$.next();
				if (RobotiumUtils.checkAndGetMatches(regex, view, uniqueTextViews) == expectedMinimumNumberOfMatches)
				{
					uniqueTextViews.clear();
					return true;
				}
			}

			if (scroll && !scroller.scroll(0))
				return logMatchesFoundAndReturnFalse(regex);
			if (!scroll)
				return logMatchesFoundAndReturnFalse(regex);
			sleeper.sleep();
		} while (true);
	}

	public int getNumberOfUniqueViews(Set uniqueViews, ArrayList views)
	{
		for (int i = 0; i < views.size(); i++)
			uniqueViews.add(views.get(i));

		numberOfUniqueViews = uniqueViews.size();
		return numberOfUniqueViews;
	}

	public int getNumberOfUniqueViews()
	{
		return numberOfUniqueViews;
	}

	private boolean logMatchesFoundAndReturnFalse(String regex)
	{
		if (uniqueTextViews.size() > 0)
			Log.d("Robotium", (new StringBuilder()).append(" There are only ").append(uniqueTextViews.size()).append(" matches of ").append(regex).toString());
		uniqueTextViews.clear();
		return false;
	}


}
