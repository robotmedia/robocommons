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
package net.robotmedia.app;

import java.net.URISyntaxException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class WebViewActivity extends Activity {

	public static final String EXTRA_URL = "src";
	
	protected WebView mWebView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebView = new WebView(this);
		setContentView(mWebView);
		
		final Intent intent = getIntent();
		final String source = intent.getStringExtra(EXTRA_URL);
		if (source != null) {
			mWebView.loadUrl(source);
			mWebView.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					WebViewActivity.this.setTitle(view.getTitle());
				}
				
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {     
					return WebViewActivity.this.shouldOverrideUrlLoading(view, url);
				}
			});
		}
	}

	public static void showAsset(Context context, String asset, Class<? extends WebViewActivity> activityClass) {
		final Intent intent = new Intent(context, activityClass);
		intent.putExtra(WebViewActivity.EXTRA_URL, "file:///android_asset/" + asset);
		context.startActivity(intent);		
	}
	
	public static void showAsset(Context context, String asset) {
		showAsset(context, asset, WebViewActivity.class);
	}
	
	protected boolean shouldOverrideUrlLoading(WebView view, String url) {
	    if (MailTo.isMailTo(url)){
	    	final Intent intent;
			try {
				intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
			} catch (URISyntaxException e) {
				return false;
			}
	        startActivity(intent);
	        return true;
	    }
	    return false;
	}
	
}
