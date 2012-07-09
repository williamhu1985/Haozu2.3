// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Checker.java

package com.jayway.android.robotium.solo;

import android.widget.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Waiter, ViewFetcher

class Checker
{

	private final ViewFetcher viewFetcher;
	private final Waiter waiter;

	public Checker(ViewFetcher viewFetcher, Waiter waiter)
	{
		this.viewFetcher = viewFetcher;
		this.waiter = waiter;
	}

	public boolean isButtonChecked(Class expectedClass, int index)
	{
		return ((CompoundButton)waiter.waitForAndGetView(index, expectedClass)).isChecked();
	}

	public boolean isButtonChecked(Class expectedClass, String text)
	{
		waiter.waitForText(text, 0, 10000L);
		ArrayList list = viewFetcher.getCurrentViews(expectedClass);
		for (Iterator i$ = list.iterator(); i$.hasNext();)
		{
			CompoundButton button = (CompoundButton)i$.next();
			if (button.getText().equals(text) && button.isChecked())
				return true;
		}

		return false;
	}

	public boolean isCheckedTextChecked(String text)
	{
		waiter.waitForText(text, 0, 10000L);
		ArrayList list = viewFetcher.getCurrentViews(android/widget/CheckedTextView);
		for (Iterator i$ = list.iterator(); i$.hasNext();)
		{
			CheckedTextView checkedText = (CheckedTextView)i$.next();
			if (checkedText.getText().equals(text) && checkedText.isChecked())
				return true;
		}

		return false;
	}

	public boolean isSpinnerTextSelected(String text)
	{
		waiter.waitForAndGetView(0, android/widget/Spinner);
		ArrayList spinnerList = viewFetcher.getCurrentViews(android/widget/Spinner);
		for (int i = 0; i < spinnerList.size(); i++)
			if (isSpinnerTextSelected(i, text))
				return true;

		return false;
	}

	public boolean isSpinnerTextSelected(int spinnerIndex, String text)
	{
		Spinner spinner = (Spinner)waiter.waitForAndGetView(spinnerIndex, android/widget/Spinner);
		TextView textView = (TextView)spinner.getChildAt(0);
		return textView.getText().equals(text);
	}
}
