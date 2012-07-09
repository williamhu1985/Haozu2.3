// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Asserter.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.app.ActivityManager;
import java.util.ArrayList;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Waiter, ActivityUtils

class Asserter
{

	private final ActivityUtils activityUtils;
	private final Waiter waiter;

	public Asserter(ActivityUtils activityUtils, Waiter waiter)
	{
		this.activityUtils = activityUtils;
		this.waiter = waiter;
	}

	public void assertCurrentActivity(String message, String name)
	{
		waiter.waitForActivity(name);
		Assert.assertEquals(message, name, activityUtils.getCurrentActivity().getClass().getSimpleName());
	}

	public void assertCurrentActivity(String message, Class expectedClass)
	{
		waiter.waitForActivity(expectedClass.getSimpleName());
		Assert.assertEquals(message, expectedClass.getName(), activityUtils.getCurrentActivity().getClass().getName());
	}

	public void assertCurrentActivity(String message, String name, boolean isNewInstance)
	{
		assertCurrentActivity(message, name);
		assertCurrentActivity(message, activityUtils.getCurrentActivity().getClass(), isNewInstance);
	}

	public void assertCurrentActivity(String message, Class expectedClass, boolean isNewInstance)
	{
		boolean found = false;
		assertCurrentActivity(message, expectedClass);
		Activity activity = activityUtils.getCurrentActivity(false);
		for (int i = 0; i < activityUtils.getAllOpenedActivities().size() - 1; i++)
		{
			String instanceString = ((Activity)activityUtils.getAllOpenedActivities().get(i)).toString();
			if (instanceString.equals(activity.toString()))
				found = true;
		}

		Assert.assertNotSame((new StringBuilder()).append(message).append(", isNewInstance: actual and ").toString(), Boolean.valueOf(isNewInstance), Boolean.valueOf(found));
	}

	public void assertMemoryNotLow()
	{
		android.app.ActivityManager.MemoryInfo mi = new android.app.ActivityManager.MemoryInfo();
		((ActivityManager)activityUtils.getCurrentActivity().getSystemService("activity")).getMemoryInfo(mi);
		Assert.assertFalse((new StringBuilder()).append("Low memory available: ").append(mi.availMem).append(" bytes").toString(), mi.lowMemory);
	}
}
