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

public class HorizontalScrollView extends android.widget.HorizontalScrollView {
	
	public interface OnScrollChangedListener {
	    void onScrollChanged(android.widget.HorizontalScrollView scrollView, 
	                         int x, int y, int oldx, int oldy);
	}
	
	private OnScrollChangedListener mOnScrollChangedListener;
	
	public HorizontalScrollView(Context context) {
		super(context);
	}
	
    public HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        this.mOnScrollChangedListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
        	mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
	
}
