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

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollView extends android.widget.ScrollView {

	public ScrollView(Context context) {
		super(context);
	}
	
    public ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    private boolean mScrollable = true;

    public void setScrollable(boolean scrollable) {
        mScrollable = scrollable;
    }

    public boolean isScrollable() {
        return mScrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return mScrollable ? super.onTouchEvent(ev) : false;
            default:
                return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mScrollable ? super.onInterceptTouchEvent(ev) : false;
    }

}
