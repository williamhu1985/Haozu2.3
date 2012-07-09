// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DialogUtils.java

package com.jayway.android.robotium.solo;


// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper, ViewFetcher

class DialogUtils
{

	private final ViewFetcher viewFetcher;
	private final Sleeper sleeper;

	public DialogUtils(ViewFetcher viewFetcher, Sleeper sleeper)
	{
		this.viewFetcher = viewFetcher;
		this.sleeper = sleeper;
	}

	public boolean waitForDialogToClose(long timeout)
	{
		sleeper.sleepMini();
		int elementsBefore = viewFetcher.getWindowDecorViews().length;
		long now = System.currentTimeMillis();
		long endTime = now + timeout;
		do
		{
			if (now >= endTime)
				break;
			int elementsNow = viewFetcher.getWindowDecorViews().length;
			if (elementsBefore < elementsNow)
				elementsBefore = elementsNow;
			if (elementsBefore > elementsNow)
				break;
			sleeper.sleepMini();
			now = System.currentTimeMillis();
		} while (true);
		return now <= endTime;
	}
}
