// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Setter.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.widget.*;

// Referenced classes of package com.jayway.android.robotium.solo:
//			ActivityUtils

class Setter
{

	private final int CLOSED = 0;
	private final int OPENED = 1;
	private final ActivityUtils activityUtils;

	public Setter(ActivityUtils activityUtils)
	{
		this.activityUtils = activityUtils;
	}

	public void setDatePicker(final DatePicker datePicker, final int year, final int monthOfYear, final int dayOfMonth)
	{
		if (datePicker != null)
			activityUtils.getCurrentActivity(false).runOnUiThread(new Runnable() {

				final DatePicker val$datePicker;
				final int val$year;
				final int val$monthOfYear;
				final int val$dayOfMonth;
				final Setter this$0;

				public void run()
				{
					try
					{
						datePicker.updateDate(year, monthOfYear, dayOfMonth);
					}
					catch (Exception ignored) { }
				}

			
			{
				this$0 = Setter.this;
				datePicker = datepicker;
				year = i;
				monthOfYear = j;
				dayOfMonth = k;
				super();
			}
			});
	}

	public void setTimePicker(final TimePicker timePicker, final int hour, final int minute)
	{
		if (timePicker != null)
			activityUtils.getCurrentActivity(false).runOnUiThread(new Runnable() {

				final TimePicker val$timePicker;
				final int val$hour;
				final int val$minute;
				final Setter this$0;

				public void run()
				{
					try
					{
						timePicker.setCurrentHour(Integer.valueOf(hour));
						timePicker.setCurrentMinute(Integer.valueOf(minute));
					}
					catch (Exception ignored) { }
				}

			
			{
				this$0 = Setter.this;
				timePicker = timepicker;
				hour = i;
				minute = j;
				super();
			}
			});
	}

	public void setProgressBar(final ProgressBar progressBar, final int progress)
	{
		if (progressBar != null)
			activityUtils.getCurrentActivity(false).runOnUiThread(new Runnable() {

				final ProgressBar val$progressBar;
				final int val$progress;
				final Setter this$0;

				public void run()
				{
					try
					{
						progressBar.setProgress(progress);
					}
					catch (Exception ignored) { }
				}

			
			{
				this$0 = Setter.this;
				progressBar = progressbar;
				progress = i;
				super();
			}
			});
	}

	public void setSlidingDrawer(final SlidingDrawer slidingDrawer, final int status)
	{
		if (slidingDrawer != null)
			activityUtils.getCurrentActivity(false).runOnUiThread(new Runnable() {

				final int val$status;
				final SlidingDrawer val$slidingDrawer;
				final Setter this$0;

				public void run()
				{
					try
					{
						switch (status)
						{
						case 0: // '\0'
							slidingDrawer.close();
							break;

						case 1: // '\001'
							slidingDrawer.open();
							break;
						}
					}
					catch (Exception ignored) { }
				}

			
			{
				this$0 = Setter.this;
				status = i;
				slidingDrawer = slidingdrawer;
				super();
			}
			});
	}
}
