// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ActivityUtils.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper

class ActivityUtils
{

	private final Instrumentation inst;
	private android.app.Instrumentation.ActivityMonitor activityMonitor;
	private Activity activity;
	private final Sleeper sleeper;
	private LinkedHashSet activityList;
	private final String LOG_TAG = "Robotium";
	private final int MINISLEEP = 100;

	public ActivityUtils(Instrumentation inst, Activity activity, Sleeper sleeper)
	{
		this.inst = inst;
		this.activity = activity;
		this.sleeper = sleeper;
		activityList = new LinkedHashSet();
		setupActivityMonitor();
	}

	public ArrayList getAllOpenedActivities()
	{
		return new ArrayList(activityList);
	}

	private void setupActivityMonitor()
	{
		try
		{
			android.content.IntentFilter filter = null;
			activityMonitor = inst.addMonitor(filter, null, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public android.app.Instrumentation.ActivityMonitor getActivityMonitor()
	{
		return activityMonitor;
	}

	public void setActivityOrientation(int orientation)
	{
		Activity activity = getCurrentActivity();
		activity.setRequestedOrientation(orientation);
	}

	public Activity getCurrentActivity()
	{
		return getCurrentActivity(true);
	}

	private final void waitForActivityIfNotAvailable()
	{
		if (activity == null)
			if (activityMonitor != null)
			{
				for (; activityMonitor.getLastActivity() == null; sleeper.sleepMini());
			} else
			{
				sleeper.sleepMini();
				setupActivityMonitor();
				waitForActivityIfNotAvailable();
			}
	}

	public Activity getCurrentActivity(boolean shouldSleepFirst)
	{
		if (shouldSleepFirst)
			sleeper.sleep();
		waitForActivityIfNotAvailable();
		if (activityMonitor != null && activityMonitor.getLastActivity() != null)
			activity = activityMonitor.getLastActivity();
		activityList.add(activity);
		return activity;
	}

	public void goBackToActivity(String name)
	{
		ArrayList activitiesOpened = getAllOpenedActivities();
		boolean found = false;
		int i = 0;
		do
		{
			if (i >= activitiesOpened.size())
				break;
			if (((Activity)activitiesOpened.get(i)).getClass().getSimpleName().equals(name))
			{
				found = true;
				break;
			}
			i++;
		} while (true);
		if (found)
		{
			while (!getCurrentActivity().getClass().getSimpleName().equals(name)) 
				try
				{
					inst.sendKeyDownUpSync(4);
				}
				catch (SecurityException ignored) { }
		} else
		{
			for (ignored = 0; ignored < activitiesOpened.size(); ignored++)
				Log.d("Robotium", (new StringBuilder()).append("Activity priorly opened: ").append(((Activity)activitiesOpened.get(ignored)).getClass().getSimpleName()).toString());

			Assert.assertTrue((new StringBuilder()).append("No Activity named ").append(name).append(" has been priorly opened").toString(), false);
		}
	}

	public String getString(int resId)
	{
		Activity activity = getCurrentActivity(false);
		return activity.getString(resId);
	}

	public void finalize()
		throws Throwable
	{
		try
		{
			if (activityMonitor != null)
				inst.removeMonitor(activityMonitor);
		}
		catch (Exception ignored) { }
		super.finalize();
	}

	public void finishOpenedActivities()
	{
		ArrayList activitiesOpened = getAllOpenedActivities();
		for (int i = activitiesOpened.size() - 1; i >= 0; i--)
		{
			sleeper.sleep(100);
			finishActivity((Activity)activitiesOpened.get(i));
		}

		finishActivity(getCurrentActivity());
		sleeper.sleepMini();
		try
		{
			inst.sendKeyDownUpSync(4);
			sleeper.sleep(100);
			inst.sendKeyDownUpSync(4);
		}
		catch (Throwable ignored) { }
		activityList.clear();
	}

	private void finishActivity(Activity activity)
	{
		try
		{
			activity.finish();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
}
