// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Scroller.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

// Referenced classes of package com.jayway.android.robotium.solo:
//			ViewFetcher, RobotiumUtils, Sleeper, ActivityUtils

class Scroller
{
	public static final class Side extends Enum
	{

		public static final Side LEFT;
		public static final Side RIGHT;
		private static final Side $VALUES[];

		public static Side[] values()
		{
			return (Side[])$VALUES.clone();
		}

		public static Side valueOf(String name)
		{
			return (Side)Enum.valueOf(com/jayway/android/robotium/solo/Scroller$Side, name);
		}

		static 
		{
			LEFT = new Side("LEFT", 0);
			RIGHT = new Side("RIGHT", 1);
			$VALUES = (new Side[] {
				LEFT, RIGHT
			});
		}

		private Side(String s, int i)
		{
			super(s, i);
		}
	}

	public static final class Direction extends Enum
	{

		public static final Direction UP;
		public static final Direction DOWN;
		private static final Direction $VALUES[];

		public static Direction[] values()
		{
			return (Direction[])$VALUES.clone();
		}

		public static Direction valueOf(String name)
		{
			return (Direction)Enum.valueOf(com/jayway/android/robotium/solo/Scroller$Direction, name);
		}

		static 
		{
			UP = new Direction("UP", 0);
			DOWN = new Direction("DOWN", 1);
			$VALUES = (new Direction[] {
				UP, DOWN
			});
		}

		private Direction(String s, int i)
		{
			super(s, i);
		}
	}


	public static final int DOWN = 0;
	public static final int UP = 1;
	private final Instrumentation inst;
	private final ActivityUtils activityUtils;
	private final ViewFetcher viewFetcher;
	private final Sleeper sleeper;

	public Scroller(Instrumentation inst, ActivityUtils activityUtils, ViewFetcher viewFetcher, Sleeper sleeper)
	{
		this.inst = inst;
		this.activityUtils = activityUtils;
		this.viewFetcher = viewFetcher;
		this.sleeper = sleeper;
	}

	public void drag(float fromX, float toX, float fromY, float toY, int stepCount)
	{
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		float y = fromY;
		float x = fromX;
		float yStep = (toY - fromY) / (float)stepCount;
		float xStep = (toX - fromX) / (float)stepCount;
		MotionEvent event = MotionEvent.obtain(downTime, eventTime, 0, fromX, fromY, 0);
		try
		{
			inst.sendPointerSync(event);
		}
		catch (SecurityException ignored) { }
		for (int i = 0; i < stepCount; i++)
		{
			y += yStep;
			x += xStep;
			eventTime = SystemClock.uptimeMillis();
			event = MotionEvent.obtain(downTime, eventTime, 2, x, y, 0);
			try
			{
				inst.sendPointerSync(event);
			}
			catch (SecurityException ignored) { }
		}

		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, 1, toX, toY, 0);
		try
		{
			inst.sendPointerSync(event);
		}
		catch (SecurityException ignored) { }
	}

	private boolean scrollScrollView(int direction, ArrayList scrollViews)
	{
		ScrollView scroll = (ScrollView)viewFetcher.getView(android/widget/ScrollView, scrollViews);
		int scrollAmount = 0;
		if (scroll != null)
		{
			int height = scroll.getHeight();
			height--;
			int scrollTo = 0;
			if (direction == 0)
				scrollTo = height;
			else
			if (direction == 1)
				scrollTo = -height;
			scrollAmount = scroll.getScrollY();
			scrollScrollViewTo(scroll, 0, scrollTo);
			return scrollAmount != scroll.getScrollY();
		} else
		{
			return false;
		}
	}

	private void scrollScrollViewTo(final ScrollView scrollView, final int x, final int y)
	{
		inst.runOnMainSync(new Runnable() {

			final ScrollView val$scrollView;
			final int val$x;
			final int val$y;
			final Scroller this$0;

			public void run()
			{
				scrollView.scrollBy(x, y);
			}

			
			{
				this$0 = Scroller.this;
				scrollView = scrollview;
				x = i;
				y = j;
				super();
			}
		});
	}

	public boolean scroll(int direction)
	{
		ArrayList viewList = RobotiumUtils.removeInvisibleViews(viewFetcher.getViews(null, true));
		ArrayList listViews = RobotiumUtils.filterViews(android/widget/ListView, viewList);
		if (listViews.size() > 0)
			return scrollList(android/widget/ListView, null, direction, listViews);
		ArrayList gridViews = RobotiumUtils.filterViews(android/widget/GridView, viewList);
		if (gridViews.size() > 0)
			return scrollList(android/widget/GridView, null, direction, gridViews);
		ArrayList scrollViews = RobotiumUtils.filterViews(android/widget/ScrollView, viewList);
		if (scrollViews.size() > 0)
			return scrollScrollView(direction, scrollViews);
		else
			return false;
	}

	public boolean scrollList(Class classToFilterBy, AbsListView absListView, int direction, ArrayList listViews)
	{
		if (absListView == null)
			absListView = (AbsListView)viewFetcher.getView(classToFilterBy, listViews);
		if (absListView == null)
			return false;
		if (direction == 0)
		{
			if (absListView.getLastVisiblePosition() >= absListView.getCount() - 1)
			{
				scrollListToLine(absListView, absListView.getLastVisiblePosition());
				return false;
			}
			if (absListView.getFirstVisiblePosition() != absListView.getLastVisiblePosition())
				scrollListToLine(absListView, absListView.getLastVisiblePosition());
			else
				scrollListToLine(absListView, absListView.getFirstVisiblePosition() + 1);
		} else
		if (direction == 1)
		{
			if (absListView.getFirstVisiblePosition() < 2)
			{
				scrollListToLine(absListView, 0);
				return false;
			}
			int lines = absListView.getLastVisiblePosition() - absListView.getFirstVisiblePosition();
			int lineToScrollTo = absListView.getFirstVisiblePosition() - lines;
			if (lineToScrollTo == absListView.getLastVisiblePosition())
				lineToScrollTo--;
			if (lineToScrollTo < 0)
				lineToScrollTo = 0;
			scrollListToLine(absListView, lineToScrollTo);
		}
		sleeper.sleep();
		return true;
	}

	private void scrollListToLine(final AbsListView view, int line)
	{
		final int lineToMoveTo;
		if (view instanceof GridView)
			lineToMoveTo = line + 1;
		else
			lineToMoveTo = line;
		inst.runOnMainSync(new Runnable() {

			final AbsListView val$view;
			final int val$lineToMoveTo;
			final Scroller this$0;

			public void run()
			{
				view.setSelection(lineToMoveTo);
			}

			
			{
				this$0 = Scroller.this;
				view = abslistview;
				lineToMoveTo = i;
				super();
			}
		});
	}

	public void scrollToSide(Side side)
	{
		int screenHeight = activityUtils.getCurrentActivity().getWindowManager().getDefaultDisplay().getHeight();
		int screenWidth = activityUtils.getCurrentActivity(false).getWindowManager().getDefaultDisplay().getWidth();
		float x = (float)screenWidth / 2.0F;
		float y = (float)screenHeight / 2.0F;
		if (side == Side.LEFT)
			drag(0.0F, x, y, y, 40);
		else
		if (side == Side.RIGHT)
			drag(x, 0.0F, y, y, 40);
	}
}
