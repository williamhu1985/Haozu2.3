// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Getter.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Waiter, ViewFetcher, RobotiumUtils, ActivityUtils

class Getter
{

	private final ActivityUtils activityUtils;
	private final ViewFetcher viewFetcher;
	private final Waiter waiter;

	public Getter(ActivityUtils activityUtils, ViewFetcher viewFetcher, Waiter waiter)
	{
		this.activityUtils = activityUtils;
		this.viewFetcher = viewFetcher;
		this.waiter = waiter;
	}

	public View getView(Class classToFilterBy, int index)
	{
		return waiter.waitForAndGetView(index, classToFilterBy);
	}

	public TextView getView(Class classToFilterBy, String text, boolean onlyVisible)
	{
		waiter.waitForText(text, 0, 10000L, false, onlyVisible);
		ArrayList views = viewFetcher.getCurrentViews(classToFilterBy);
		if (onlyVisible)
			views = RobotiumUtils.removeInvisibleViews(views);
		TextView viewToReturn = null;
		Iterator i$ = views.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			TextView view = (TextView)i$.next();
			if (view.getText().toString().equals(text))
				viewToReturn = view;
		} while (true);
		if (viewToReturn == null)
			Assert.assertTrue((new StringBuilder()).append("No ").append(classToFilterBy.getSimpleName()).append(" with text ").append(text).append(" is found!").toString(), false);
		return viewToReturn;
	}

	public View getView(int id)
	{
		Activity activity = activityUtils.getCurrentActivity(false);
		View view = activity.findViewById(id);
		if (view != null)
			return view;
		else
			return waiter.waitForView(id);
	}
}
