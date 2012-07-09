// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ViewFetcher.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.os.Build;
import android.view.*;
import android.widget.AbsListView;
import android.widget.ScrollView;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.jayway.android.robotium.solo:
//			ActivityUtils, RobotiumUtils

class ViewFetcher
{

	private final ActivityUtils activityUtils;
	private String windowManagerString;
	private static Class windowManager;

	public ViewFetcher(ActivityUtils activityUtils)
	{
		this.activityUtils = activityUtils;
		setWindowManagerString();
	}

	public View getTopParent(View view)
	{
		if (view.getParent() != null && !view.getParent().getClass().getName().equals("android.view.ViewRoot"))
			return getTopParent((View)view.getParent());
		else
			return view;
	}

	public View getScrollOrListParent(View view)
	{
		if ((view instanceof AbsListView) || (view instanceof ScrollView))
			break MISSING_BLOCK_LABEL_29;
		return getScrollOrListParent((View)view.getParent());
		Exception e;
		e;
		return null;
		return view;
	}

	public ArrayList getAllViews(boolean onlySufficientlyVisible)
	{
		View views[] = getWindowDecorViews();
		ArrayList allViews = new ArrayList();
		View nonDecorViews[] = getNonDecorViews(views);
		if (views != null && views.length > 0)
		{
			View view;
			for (int i = 0; i < nonDecorViews.length; i++)
			{
				view = nonDecorViews[i];
				try
				{
					addChildren(allViews, (ViewGroup)view, onlySufficientlyVisible);
				}
				catch (Exception ignored) { }
			}

			view = getRecentDecorView(views);
			try
			{
				addChildren(allViews, (ViewGroup)view, onlySufficientlyVisible);
			}
			catch (Exception ignored) { }
		}
		return allViews;
	}

	public final View getRecentDecorView(View views[])
	{
		View decorViews[] = new View[views.length];
		int i = 0;
		for (int j = 0; j < views.length; j++)
		{
			View view = views[j];
			if (view.getClass().getName().equals("com.android.internal.policy.impl.PhoneWindow$DecorView"))
			{
				decorViews[i] = view;
				i++;
			}
		}

		return getRecentContainer(decorViews);
	}

	private final View getRecentContainer(View views[])
	{
		View container = null;
		long drawingTime = 0L;
		for (int i = 0; i < views.length; i++)
		{
			View view = views[i];
			if (view != null && view.isShown() && view.hasWindowFocus() && view.getDrawingTime() > drawingTime)
			{
				container = view;
				drawingTime = view.getDrawingTime();
			}
		}

		return container;
	}

	private final View[] getNonDecorViews(View views[])
	{
		View decorViews[] = new View[views.length];
		int i = 0;
		for (int j = 0; j < views.length; j++)
		{
			View view = views[j];
			if (!view.getClass().getName().equals("com.android.internal.policy.impl.PhoneWindow$DecorView"))
			{
				decorViews[i] = view;
				i++;
			}
		}

		return decorViews;
	}

	public ArrayList getViews(View parent, boolean onlySufficientlyVisible)
	{
		activityUtils.getCurrentActivity(false);
		ArrayList views = new ArrayList();
		if (parent == null)
			return getAllViews(onlySufficientlyVisible);
		View parentToUse = parent;
		views.add(parentToUse);
		if (parentToUse instanceof ViewGroup)
			addChildren(views, (ViewGroup)parentToUse, onlySufficientlyVisible);
		return views;
	}

	private void addChildren(ArrayList views, ViewGroup viewGroup, boolean onlySufficientlyVisible)
	{
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			View child = viewGroup.getChildAt(i);
			if (onlySufficientlyVisible && isViewSufficientlyShown(child))
				views.add(child);
			else
			if (!onlySufficientlyVisible)
				views.add(child);
			if (child instanceof ViewGroup)
				addChildren(views, (ViewGroup)child, onlySufficientlyVisible);
		}

	}

	public final boolean isViewSufficientlyShown(View view)
	{
		int xyView[] = new int[2];
		int xyParent[] = new int[2];
		if (view == null)
			return false;
		float viewHeight = view.getHeight();
		View parent = getScrollOrListParent(view);
		view.getLocationOnScreen(xyView);
		if (parent == null)
			xyParent[1] = 0;
		else
			parent.getLocationOnScreen(xyParent);
		if ((float)xyView[1] + viewHeight / 2.0F > getScrollListWindowHeight(view))
			return false;
		return (float)xyView[1] + viewHeight / 2.0F >= (float)xyParent[1];
	}

	public float getScrollListWindowHeight(View view)
	{
		int xyParent[] = new int[2];
		View parent = getScrollOrListParent(view);
		float windowHeight;
		if (parent == null)
		{
			windowHeight = activityUtils.getCurrentActivity(false).getWindowManager().getDefaultDisplay().getHeight();
		} else
		{
			parent.getLocationOnScreen(xyParent);
			windowHeight = xyParent[1] + parent.getHeight();
		}
		parent = null;
		return windowHeight;
	}

	public ArrayList getCurrentViews(Class classToFilterBy)
	{
		return getCurrentViews(classToFilterBy, null);
	}

	public ArrayList getCurrentViews(Class classToFilterBy, View parent)
	{
		ArrayList filteredViews = new ArrayList();
		List allViews = getViews(parent, true);
		Iterator i$ = allViews.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			View view = (View)i$.next();
			if (view != null && classToFilterBy.isAssignableFrom(view.getClass()))
				filteredViews.add(classToFilterBy.cast(view));
		} while (true);
		allViews = null;
		return filteredViews;
	}

	public final View getView(Class classToFilterBy, ArrayList views)
	{
		View viewToReturn = null;
		long drawingTime = 0L;
		if (views == null)
			views = RobotiumUtils.removeInvisibleViews(getCurrentViews(classToFilterBy));
		Iterator i$ = views.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			View view = (View)i$.next();
			if (view.getDrawingTime() > drawingTime && view.getHeight() > 0)
			{
				drawingTime = view.getDrawingTime();
				viewToReturn = view;
			}
		} while (true);
		views = null;
		return viewToReturn;
	}

	public View[] getWindowDecorViews()
	{
		Field viewsField;
		Object instance;
		viewsField = windowManager.getDeclaredField("mViews");
		Field instanceField = windowManager.getDeclaredField(windowManagerString);
		viewsField.setAccessible(true);
		instanceField.setAccessible(true);
		instance = instanceField.get(null);
		return (View[])(View[])viewsField.get(instance);
		SecurityException e;
		e;
		e.printStackTrace();
		break MISSING_BLOCK_LABEL_77;
		e;
		e.printStackTrace();
		break MISSING_BLOCK_LABEL_77;
		e;
		e.printStackTrace();
		break MISSING_BLOCK_LABEL_77;
		e;
		e.printStackTrace();
		return null;
	}

	private void setWindowManagerString()
	{
		if (android.os.Build.VERSION.SDK_INT >= 13)
			windowManagerString = "sWindowManager";
		else
			windowManagerString = "mWindowManager";
	}

	static 
	{
		try
		{
			windowManager = Class.forName("android.view.WindowManagerImpl");
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
	}
}
