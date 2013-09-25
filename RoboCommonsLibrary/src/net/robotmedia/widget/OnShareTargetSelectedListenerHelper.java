/*
 * (C) Copyright Robot Media SL (http://robotmedia.net) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.robotmedia.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.ShareActionProvider;
import android.widget.ShareActionProvider.OnShareTargetSelectedListener;

public class OnShareTargetSelectedListenerHelper implements OnShareTargetSelectedListener {

	protected static final String PACKAGE_TWITTER = "com.twitter.android";
	protected static final String PACKAGE_FACEBOOK = "com.facebook.katana";
	protected static final String PACKAGE_GMAIL = "com.google.android.gm";
	protected static final String PACKAGE_GOOGLE_PLUS = "com.google.android.apps.plus";

	@Override
	public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
		final ComponentName component = intent.getComponent();
		if (component == null) return false;

		final String packageName = component.getPackageName();
		if (PACKAGE_TWITTER.equals(packageName)) {
			onTwitterSelected(source, intent);
		}
		if (PACKAGE_FACEBOOK.equals(packageName)) {
			onFacebookSelected(source, intent);
		}
		if (PACKAGE_GMAIL.equals(packageName)) {
			onGmailSelected(source, intent);
		}
		if (PACKAGE_GOOGLE_PLUS.equals(packageName)) {
			onGooglePlusSelected(source, intent);
		}

		// According to the docs:
		// "The return result is ignored. Always return false for consistency."
		// Great job, Android team! 
		// See:
		// http://developer.android.com/reference/android/widget/ShareActionProvider.OnShareTargetSelectedListener.html
		return false;
	}

	protected void onGooglePlusSelected(ShareActionProvider source, Intent intent) {
	}

	protected void onGmailSelected(ShareActionProvider source, Intent intent) {
	}

	protected void onFacebookSelected(ShareActionProvider source, Intent intent) {
	}

	protected void onTwitterSelected(ShareActionProvider source, Intent intent) {
	}

	protected void startActivityByClearingTop(final Context context, Intent intent) {
		final Intent finalIntent = new Intent(intent);
		finalIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				context.startActivity(finalIntent);
			}
		}, 1);
	}

}
