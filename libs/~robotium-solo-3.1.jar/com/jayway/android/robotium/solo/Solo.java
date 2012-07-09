// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Solo.java

package com.jayway.android.robotium.solo;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

// Referenced classes of package com.jayway.android.robotium.solo:
//			Sleeper, ActivityUtils, ViewFetcher, DialogUtils, 
//			Scroller, Searcher, Waiter, Setter, 
//			Getter, Asserter, Checker, RobotiumUtils, 
//			Clicker, Presser, TextEnterer

public class Solo
{

	private final Asserter asserter;
	private final ViewFetcher viewFetcher;
	private final Checker checker;
	private final Clicker clicker;
	private final Presser presser;
	private final Searcher searcher;
	private final ActivityUtils activityUtils;
	private final DialogUtils dialogUtils;
	private final TextEnterer textEnterer;
	private final Scroller scroller;
	private final RobotiumUtils robotiumUtils;
	private final Sleeper sleeper;
	private final Waiter waiter;
	private final Setter setter;
	private final Getter getter;
	private final int TIMEOUT = 20000;
	private final int SMALLTIMEOUT = 10000;
	public static final int LANDSCAPE = 0;
	public static final int PORTRAIT = 1;
	public static final int RIGHT = 22;
	public static final int LEFT = 21;
	public static final int UP = 19;
	public static final int DOWN = 20;
	public static final int ENTER = 66;
	public static final int MENU = 82;
	public static final int DELETE = 67;
	public static final int CLOSED = 0;
	public static final int OPENED = 1;

	public Solo(Instrumentation instrumentation, Activity activity)
	{
		sleeper = new Sleeper();
		activityUtils = new ActivityUtils(instrumentation, activity, sleeper);
		viewFetcher = new ViewFetcher(activityUtils);
		dialogUtils = new DialogUtils(viewFetcher, sleeper);
		scroller = new Scroller(instrumentation, activityUtils, viewFetcher, sleeper);
		searcher = new Searcher(viewFetcher, scroller, sleeper);
		waiter = new Waiter(activityUtils, viewFetcher, searcher, scroller, sleeper);
		setter = new Setter(activityUtils);
		getter = new Getter(activityUtils, viewFetcher, waiter);
		asserter = new Asserter(activityUtils, waiter);
		checker = new Checker(viewFetcher, waiter);
		robotiumUtils = new RobotiumUtils(instrumentation, sleeper);
		clicker = new Clicker(viewFetcher, scroller, robotiumUtils, instrumentation, sleeper, waiter);
		presser = new Presser(clicker, instrumentation, sleeper, waiter);
		textEnterer = new TextEnterer(instrumentation);
	}

	public Solo(Instrumentation instrumentation)
	{
		this(instrumentation, null);
	}

	public android.app.Instrumentation.ActivityMonitor getActivityMonitor()
	{
		return activityUtils.getActivityMonitor();
	}

	public ArrayList getViews()
	{
		return viewFetcher.getViews(null, false);
		Exception e;
		e;
		e.printStackTrace();
		return null;
	}

	public ArrayList getViews(View parent)
	{
		return viewFetcher.getViews(parent, false);
		Exception e;
		e;
		e.printStackTrace();
		return null;
	}

	public View getTopParent(View view)
	{
		View topParent = viewFetcher.getTopParent(view);
		return topParent;
	}

	public boolean waitForText(String text)
	{
		return waiter.waitForText(text);
	}

	public boolean waitForText(String text, int minimumNumberOfMatches, long timeout)
	{
		return waiter.waitForText(text, minimumNumberOfMatches, timeout);
	}

	public boolean waitForText(String text, int minimumNumberOfMatches, long timeout, boolean scroll)
	{
		return waiter.waitForText(text, minimumNumberOfMatches, timeout, scroll);
	}

	public boolean waitForText(String text, int minimumNumberOfMatches, long timeout, boolean scroll, boolean onlyVisible)
	{
		return waiter.waitForText(text, minimumNumberOfMatches, timeout, scroll, onlyVisible);
	}

	public boolean waitForView(Class viewClass)
	{
		return waiter.waitForView(viewClass, 0, 20000, true);
	}

	public boolean waitForView(View view)
	{
		return waiter.waitForView(view);
	}

	public boolean waitForView(View view, int timeout, boolean scroll)
	{
		return waiter.waitForView(view, timeout, scroll);
	}

	public boolean waitForView(Class viewClass, int minimumNumberOfMatches, int timeout)
	{
		int index = minimumNumberOfMatches - 1;
		if (index < 1)
			index = 0;
		return waiter.waitForView(viewClass, index, timeout, true);
	}

	public boolean waitForView(Class viewClass, int minimumNumberOfMatches, int timeout, boolean scroll)
	{
		int index = minimumNumberOfMatches - 1;
		if (index < 1)
			index = 0;
		return waiter.waitForView(viewClass, index, timeout, scroll);
	}

	public boolean searchEditText(String text)
	{
		return searcher.searchWithTimeoutFor(android/widget/EditText, text, 1, true, false);
	}

	public boolean searchButton(String text)
	{
		return searcher.searchWithTimeoutFor(android/widget/Button, text, 0, true, false);
	}

	public boolean searchButton(String text, boolean onlyVisible)
	{
		return searcher.searchWithTimeoutFor(android/widget/Button, text, 0, true, onlyVisible);
	}

	public boolean searchToggleButton(String text)
	{
		return searcher.searchWithTimeoutFor(android/widget/ToggleButton, text, 0, true, false);
	}

	public boolean searchButton(String text, int minimumNumberOfMatches)
	{
		return searcher.searchWithTimeoutFor(android/widget/Button, text, minimumNumberOfMatches, true, false);
	}

	public boolean searchButton(String text, int minimumNumberOfMatches, boolean onlyVisible)
	{
		return searcher.searchWithTimeoutFor(android/widget/Button, text, minimumNumberOfMatches, true, onlyVisible);
	}

	public boolean searchToggleButton(String text, int minimumNumberOfMatches)
	{
		return searcher.searchWithTimeoutFor(android/widget/ToggleButton, text, minimumNumberOfMatches, true, false);
	}

	public boolean searchText(String text)
	{
		return searcher.searchWithTimeoutFor(android/widget/TextView, text, 0, true, false);
	}

	public boolean searchText(String text, boolean onlyVisible)
	{
		return searcher.searchWithTimeoutFor(android/widget/TextView, text, 0, true, onlyVisible);
	}

	public boolean searchText(String text, int minimumNumberOfMatches)
	{
		return searcher.searchWithTimeoutFor(android/widget/TextView, text, minimumNumberOfMatches, true, false);
	}

	public boolean searchText(String text, int minimumNumberOfMatches, boolean scroll)
	{
		return searcher.searchWithTimeoutFor(android/widget/TextView, text, minimumNumberOfMatches, scroll, false);
	}

	public boolean searchText(String text, int minimumNumberOfMatches, boolean scroll, boolean onlyVisible)
	{
		return searcher.searchWithTimeoutFor(android/widget/TextView, text, minimumNumberOfMatches, scroll, onlyVisible);
	}

	public void setActivityOrientation(int orientation)
	{
		activityUtils.setActivityOrientation(orientation);
	}

	public ArrayList getAllOpenedActivities()
	{
		return activityUtils.getAllOpenedActivities();
	}

	public Activity getCurrentActivity()
	{
		return activityUtils.getCurrentActivity();
	}

	public void assertCurrentActivity(String message, String name)
	{
		asserter.assertCurrentActivity(message, name);
	}

	public void assertCurrentActivity(String message, Class expectedClass)
	{
		asserter.assertCurrentActivity(message, expectedClass);
	}

	public void assertCurrentActivity(String message, String name, boolean isNewInstance)
	{
		asserter.assertCurrentActivity(message, name, isNewInstance);
	}

	public void assertCurrentActivity(String message, Class expectedClass, boolean isNewInstance)
	{
		asserter.assertCurrentActivity(message, expectedClass, isNewInstance);
	}

	public void assertMemoryNotLow()
	{
		asserter.assertMemoryNotLow();
	}

	public boolean waitForDialogToClose(long timeout)
	{
		return dialogUtils.waitForDialogToClose(timeout);
	}

	public void goBack()
	{
		robotiumUtils.goBack();
	}

	public void clickOnScreen(float x, float y)
	{
		sleeper.sleep();
		clicker.clickOnScreen(x, y);
	}

	public void clickLongOnScreen(float x, float y)
	{
		clicker.clickLongOnScreen(x, y, 0);
	}

	public void clickLongOnScreen(float x, float y, int time)
	{
		clicker.clickLongOnScreen(x, y, time);
	}

	public void clickOnButton(String name)
	{
		clicker.clickOn(android/widget/Button, name);
	}

	public void clickOnImageButton(int index)
	{
		clicker.clickOn(android/widget/ImageButton, index);
	}

	public void clickOnToggleButton(String name)
	{
		clicker.clickOn(android/widget/ToggleButton, name);
	}

	public void clickOnMenuItem(String text)
	{
		clicker.clickOnMenuItem(text);
	}

	public void clickOnMenuItem(String text, boolean subMenu)
	{
		clicker.clickOnMenuItem(text, subMenu);
	}

	public void pressMenuItem(int index)
	{
		presser.pressMenuItem(index);
	}

	public void pressMenuItem(int index, int itemsPerRow)
	{
		presser.pressMenuItem(index, itemsPerRow);
	}

	public void pressSpinnerItem(int spinnerIndex, int itemIndex)
	{
		presser.pressSpinnerItem(spinnerIndex, itemIndex);
	}

	public void clickOnView(View view)
	{
		waiter.waitForView(view, 10000);
		clicker.clickOnScreen(view);
	}

	public void clickLongOnView(View view)
	{
		clicker.clickOnScreen(view, true, 0);
	}

	public void clickLongOnView(View view, int time)
	{
		clicker.clickOnScreen(view, true, time);
	}

	public void clickOnText(String text)
	{
		clicker.clickOnText(text, false, 1, true, 0);
	}

	public void clickOnText(String text, int match)
	{
		clicker.clickOnText(text, false, match, true, 0);
	}

	public void clickOnText(String text, int match, boolean scroll)
	{
		clicker.clickOnText(text, false, match, scroll, 0);
	}

	public void clickLongOnText(String text)
	{
		clicker.clickOnText(text, true, 1, true, 0);
	}

	public void clickLongOnText(String text, int match)
	{
		clicker.clickOnText(text, true, match, true, 0);
	}

	public void clickLongOnText(String text, int match, boolean scroll)
	{
		clicker.clickOnText(text, true, match, scroll, 0);
	}

	public void clickLongOnText(String text, int match, int time)
	{
		clicker.clickOnText(text, true, match, true, time);
	}

	public void clickLongOnTextAndPress(String text, int index)
	{
		clicker.clickLongOnTextAndPress(text, index);
	}

	public void clickOnButton(int index)
	{
		clicker.clickOn(android/widget/Button, index);
	}

	public void clickOnRadioButton(int index)
	{
		clicker.clickOn(android/widget/RadioButton, index);
	}

	public void clickOnCheckBox(int index)
	{
		clicker.clickOn(android/widget/CheckBox, index);
	}

	public void clickOnEditText(int index)
	{
		clicker.clickOn(android/widget/EditText, index);
	}

	public ArrayList clickInList(int line)
	{
		return clicker.clickInList(line);
	}

	public ArrayList clickInList(int line, int index)
	{
		return clicker.clickInList(line, index, false, 0);
	}

	public ArrayList clickLongInList(int line)
	{
		return clicker.clickInList(line, 0, true, 0);
	}

	public ArrayList clickLongInList(int line, int index)
	{
		return clicker.clickInList(line, index, true, 0);
	}

	public ArrayList clickLongInList(int line, int index, int time)
	{
		return clicker.clickInList(line, index, true, time);
	}

	public void drag(float fromX, float toX, float fromY, float toY, int stepCount)
	{
		scroller.drag(fromX, toX, fromY, toY, stepCount);
	}

	public boolean scrollDown()
	{
		waiter.waitForViews(android/widget/AbsListView, android/widget/ScrollView);
		return scroller.scroll(0);
	}

	public boolean scrollUp()
	{
		waiter.waitForViews(android/widget/AbsListView, android/widget/ScrollView);
		return scroller.scroll(1);
	}

	public boolean scrollDownList(int index)
	{
		return scroller.scrollList(android/widget/ListView, (AbsListView)waiter.waitForAndGetView(index, android/widget/ListView), 0, null);
	}

	public boolean scrollUpList(int index)
	{
		return scroller.scrollList(android/widget/ListView, (AbsListView)waiter.waitForAndGetView(index, android/widget/ListView), 1, null);
	}

	public void scrollToSide(int side)
	{
		switch (side)
		{
		case 22: // '\026'
			scroller.scrollToSide(Scroller.Side.RIGHT);
			break;

		case 21: // '\025'
			scroller.scrollToSide(Scroller.Side.LEFT);
			break;
		}
	}

	public void setDatePicker(int index, int year, int monthOfYear, int dayOfMonth)
	{
		setDatePicker((DatePicker)waiter.waitForAndGetView(index, android/widget/DatePicker), year, monthOfYear, dayOfMonth);
	}

	public void setDatePicker(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth)
	{
		waiter.waitForView(datePicker, 10000);
		setter.setDatePicker(datePicker, year, monthOfYear, dayOfMonth);
	}

	public void setTimePicker(int index, int hour, int minute)
	{
		setTimePicker((TimePicker)waiter.waitForAndGetView(index, android/widget/TimePicker), hour, minute);
	}

	public void setTimePicker(TimePicker timePicker, int hour, int minute)
	{
		waiter.waitForView(timePicker, 10000);
		setter.setTimePicker(timePicker, hour, minute);
	}

	public void setProgressBar(int index, int progress)
	{
		setProgressBar((ProgressBar)waiter.waitForAndGetView(index, android/widget/ProgressBar), progress);
	}

	public void setProgressBar(ProgressBar progressBar, int progress)
	{
		waiter.waitForView(progressBar, 10000);
		setter.setProgressBar(progressBar, progress);
	}

	public void setSlidingDrawer(int index, int status)
	{
		setSlidingDrawer((SlidingDrawer)waiter.waitForAndGetView(index, android/widget/SlidingDrawer), status);
	}

	public void setSlidingDrawer(SlidingDrawer slidingDrawer, int status)
	{
		waiter.waitForView(slidingDrawer, 10000);
		setter.setSlidingDrawer(slidingDrawer, status);
	}

	public void enterText(int index, String text)
	{
		textEnterer.setEditText((EditText)waiter.waitForAndGetView(index, android/widget/EditText), text);
	}

	public void enterText(EditText editText, String text)
	{
		waiter.waitForView(editText, 10000);
		textEnterer.setEditText(editText, text);
	}

	public void clearEditText(int index)
	{
		textEnterer.setEditText((EditText)waiter.waitForAndGetView(index, android/widget/EditText), "");
	}

	public void clearEditText(EditText editText)
	{
		waiter.waitForView(editText, 10000);
		textEnterer.setEditText(editText, "");
	}

	public void clickOnImage(int index)
	{
		clicker.clickOn(android/widget/ImageView, index);
	}

	public EditText getEditText(int index)
	{
		return (EditText)getter.getView(android/widget/EditText, index);
	}

	public Button getButton(int index)
	{
		return (Button)getter.getView(android/widget/Button, index);
	}

	public TextView getText(int index)
	{
		return (TextView)getter.getView(android/widget/TextView, index);
	}

	public ImageView getImage(int index)
	{
		return (ImageView)getter.getView(android/widget/ImageView, index);
	}

	public ImageButton getImageButton(int index)
	{
		return (ImageButton)getter.getView(android/widget/ImageButton, index);
	}

	public TextView getText(String text)
	{
		return getter.getView(android/widget/TextView, text, false);
	}

	public TextView getText(String text, boolean onlyVisible)
	{
		return getter.getView(android/widget/TextView, text, onlyVisible);
	}

	public Button getButton(String text)
	{
		return (Button)getter.getView(android/widget/Button, text, false);
	}

	public Button getButton(String text, boolean onlyVisible)
	{
		return (Button)getter.getView(android/widget/Button, text, onlyVisible);
	}

	public EditText getEditText(String text)
	{
		return (EditText)getter.getView(android/widget/EditText, text, false);
	}

	public EditText getEditText(String text, boolean onlyVisible)
	{
		return (EditText)getter.getView(android/widget/EditText, text, onlyVisible);
	}

	public View getView(int id)
	{
		return getter.getView(id);
	}

	public View getView(Class viewClass, int index)
	{
		return waiter.waitForAndGetView(index, viewClass);
	}

	public ArrayList getCurrentViews()
	{
		return viewFetcher.getViews(null, true);
	}

	public ArrayList getCurrentImageViews()
	{
		return viewFetcher.getCurrentViews(android/widget/ImageView);
	}

	public ArrayList getCurrentEditTexts()
	{
		return viewFetcher.getCurrentViews(android/widget/EditText);
	}

	public ArrayList getCurrentListViews()
	{
		return viewFetcher.getCurrentViews(android/widget/ListView);
	}

	public ArrayList getCurrentScrollViews()
	{
		return viewFetcher.getCurrentViews(android/widget/ScrollView);
	}

	public ArrayList getCurrentSpinners()
	{
		return viewFetcher.getCurrentViews(android/widget/Spinner);
	}

	public ArrayList getCurrentTextViews(View parent)
	{
		return viewFetcher.getCurrentViews(android/widget/TextView, parent);
	}

	public ArrayList getCurrentGridViews()
	{
		return viewFetcher.getCurrentViews(android/widget/GridView);
	}

	public ArrayList getCurrentButtons()
	{
		return viewFetcher.getCurrentViews(android/widget/Button);
	}

	public ArrayList getCurrentToggleButtons()
	{
		return viewFetcher.getCurrentViews(android/widget/ToggleButton);
	}

	public ArrayList getCurrentRadioButtons()
	{
		return viewFetcher.getCurrentViews(android/widget/RadioButton);
	}

	public ArrayList getCurrentCheckBoxes()
	{
		return viewFetcher.getCurrentViews(android/widget/CheckBox);
	}

	public ArrayList getCurrentImageButtons()
	{
		return viewFetcher.getCurrentViews(android/widget/ImageButton);
	}

	public ArrayList getCurrentDatePickers()
	{
		return viewFetcher.getCurrentViews(android/widget/DatePicker);
	}

	public ArrayList getCurrentTimePickers()
	{
		return viewFetcher.getCurrentViews(android/widget/TimePicker);
	}

	public ArrayList getCurrentSlidingDrawers()
	{
		return viewFetcher.getCurrentViews(android/widget/SlidingDrawer);
	}

	public ArrayList getCurrentProgressBars()
	{
		return viewFetcher.getCurrentViews(android/widget/ProgressBar);
	}

	public boolean isRadioButtonChecked(int index)
	{
		return checker.isButtonChecked(android/widget/RadioButton, index);
	}

	public boolean isRadioButtonChecked(String text)
	{
		return checker.isButtonChecked(android/widget/RadioButton, text);
	}

	public boolean isCheckBoxChecked(int index)
	{
		return checker.isButtonChecked(android/widget/CheckBox, index);
	}

	public boolean isToggleButtonChecked(String text)
	{
		return checker.isButtonChecked(android/widget/ToggleButton, text);
	}

	public boolean isToggleButtonChecked(int index)
	{
		return checker.isButtonChecked(android/widget/ToggleButton, index);
	}

	public boolean isCheckBoxChecked(String text)
	{
		return checker.isButtonChecked(android/widget/CheckBox, text);
	}

	public boolean isTextChecked(String text)
	{
		waiter.waitForViews(android/widget/CheckedTextView, android/widget/CompoundButton);
		if (viewFetcher.getCurrentViews(android/widget/CheckedTextView).size() > 0 && checker.isCheckedTextChecked(text))
			return true;
		return viewFetcher.getCurrentViews(android/widget/CompoundButton).size() > 0 && checker.isButtonChecked(android/widget/CompoundButton, text);
	}

	public boolean isSpinnerTextSelected(String text)
	{
		return checker.isSpinnerTextSelected(text);
	}

	public boolean isSpinnerTextSelected(int index, String text)
	{
		return checker.isSpinnerTextSelected(index, text);
	}

	public void sendKey(int key)
	{
		switch (key)
		{
		case 22: // '\026'
			robotiumUtils.sendKeyCode(22);
			break;

		case 21: // '\025'
			robotiumUtils.sendKeyCode(21);
			break;

		case 19: // '\023'
			robotiumUtils.sendKeyCode(19);
			break;

		case 20: // '\024'
			robotiumUtils.sendKeyCode(20);
			break;

		case 66: // 'B'
			robotiumUtils.sendKeyCode(66);
			break;

		case 82: // 'R'
			robotiumUtils.sendKeyCode(82);
			break;

		case 67: // 'C'
			robotiumUtils.sendKeyCode(67);
			break;

		default:
			robotiumUtils.sendKeyCode(key);
			break;
		}
	}

	public void goBackToActivity(String name)
	{
		activityUtils.goBackToActivity(name);
	}

	public boolean waitForActivity(String name)
	{
		return waiter.waitForActivity(name, 20000);
	}

	public boolean waitForActivity(String name, int timeout)
	{
		return waiter.waitForActivity(name, timeout);
	}

	public String getString(int resId)
	{
		return activityUtils.getString(resId);
	}

	public void sleep(int time)
	{
		sleeper.sleep(time);
	}

	public void finalize()
		throws Throwable
	{
		activityUtils.finalize();
	}

	public void finishOpenedActivities()
	{
		activityUtils.finishOpenedActivities();
	}
}
