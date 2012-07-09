// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Presser.java

package com.jayway.android.robotium.solo;

import android.app.Instrumentation;
import android.widget.Spinner;
import junit.framework.Assert;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper, Waiter, Clicker

class Presser
{

	private final Clicker clicker;
	private final Instrumentation inst;
	private final Sleeper sleeper;
	private final Waiter waiter;

	public Presser(Clicker clicker, Instrumentation inst, Sleeper sleeper, Waiter waiter)
	{
		this.clicker = clicker;
		this.inst = inst;
		this.sleeper = sleeper;
		this.waiter = waiter;
	}

	public void pressMenuItem(int index)
	{
		pressMenuItem(index, 3);
	}

	public void pressMenuItem(int index, int itemsPerRow)
	{
		int row[] = new int[4];
		for (int i = 1; i <= 3; i++)
			row[i] = itemsPerRow * i;

		sleeper.sleep();
		try
		{
			inst.sendKeyDownUpSync(82);
			sleeper.sleepMini();
			inst.sendKeyDownUpSync(19);
			inst.sendKeyDownUpSync(19);
		}
		catch (SecurityException e)
		{
			Assert.assertTrue("Can not press the menu!", false);
		}
		if (index < row[1])
		{
			for (int i = 0; i < index; i++)
			{
				sleeper.sleepMini();
				inst.sendKeyDownUpSync(22);
			}

		} else
		if (index >= row[1] && index < row[2])
		{
			inst.sendKeyDownUpSync(20);
			for (int i = row[1]; i < index; i++)
			{
				sleeper.sleepMini();
				inst.sendKeyDownUpSync(22);
			}

		} else
		if (index >= row[2])
		{
			inst.sendKeyDownUpSync(20);
			inst.sendKeyDownUpSync(20);
			for (int i = row[2]; i < index; i++)
			{
				sleeper.sleepMini();
				inst.sendKeyDownUpSync(22);
			}

		}
		try
		{
			inst.sendKeyDownUpSync(66);
		}
		catch (SecurityException ignored) { }
	}

	public void pressSpinnerItem(int spinnerIndex, int itemIndex)
	{
		clicker.clickOnScreen(waiter.waitForAndGetView(spinnerIndex, android/widget/Spinner));
		sleeper.sleep();
		try
		{
			inst.sendKeyDownUpSync(20);
		}
		catch (SecurityException ignored) { }
		boolean countingUp = true;
		if (itemIndex < 0)
		{
			countingUp = false;
			itemIndex *= -1;
		}
		for (int i = 0; i < itemIndex; i++)
		{
			sleeper.sleepMini();
			if (countingUp)
			{
				try
				{
					inst.sendKeyDownUpSync(20);
				}
				catch (SecurityException ignored) { }
				continue;
			}
			try
			{
				inst.sendKeyDownUpSync(19);
			}
			catch (SecurityException ignored) { }
		}

		try
		{
			inst.sendKeyDownUpSync(66);
		}
		catch (SecurityException ignored) { }
	}
}
