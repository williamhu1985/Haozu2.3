// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TextEnterer.java

package com.jayway.android.robotium.solo;

import android.app.Instrumentation;
import android.widget.EditText;
import junit.framework.Assert;

class TextEnterer
{

	private final Instrumentation inst;

	public TextEnterer(Instrumentation inst)
	{
		this.inst = inst;
	}

	public void setEditText(final EditText editText, final String text)
	{
		if (editText != null)
		{
			final String previousText = editText.getText().toString();
			if (!editText.isEnabled())
				Assert.assertTrue("Edit text is not enabled!", false);
			inst.runOnMainSync(new Runnable() {

				final EditText val$editText;
				final String val$text;
				final String val$previousText;
				final TextEnterer this$0;

				public void run()
				{
					editText.setInputType(0);
					editText.performClick();
					if (text.equals(""))
					{
						editText.setText(text);
					} else
					{
						editText.setText((new StringBuilder()).append(previousText).append(text).toString());
						editText.setCursorVisible(false);
					}
				}

			
			{
				this$0 = TextEnterer.this;
				editText = edittext;
				text = s;
				previousText = s1;
				super();
			}
			});
		}
	}
}
