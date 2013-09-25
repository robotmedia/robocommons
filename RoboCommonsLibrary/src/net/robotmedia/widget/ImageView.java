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
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class ImageView extends android.widget.ImageView {

	public ImageView(Context context) {
		super(context);
	}
	
    public ImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void fitYUniformly() {
    	final Drawable drawable = getDrawable();
    	if (drawable == null) return;
    	
		final int dwidth = drawable.getIntrinsicWidth();
		final int dheight = drawable.getIntrinsicHeight();
		if (dwidth == -1 || dheight == -1) return;

		int vheight = this.getHeight();
		float scale = (float) vheight / (float) dheight;

		final int vwidth = (int) (dwidth * scale);
		scale(scale, vwidth, vheight);
    }
    
    public void fitXUniformly() {
    	final Drawable drawable = getDrawable();
    	if (drawable == null) return;
    	
		final int dwidth = drawable.getIntrinsicWidth();
		final int dheight = drawable.getIntrinsicHeight();
		if (dwidth == -1 || dheight == -1) return;

		int vwidth = this.getWidth();
		float scale = (float) vwidth / (float) dwidth;

		final int vheight = (int) (dheight * scale);
		scale(scale, vwidth, vheight);
    }
    
    private void scale(float scale, int newWidth, int newHeight) {
		final ViewGroup.LayoutParams params = this.getLayoutParams();
		params.width = newWidth;
		params.height = newHeight;
		this.setLayoutParams(params);
		this.setScaleType(ScaleType.MATRIX);
		final Matrix matrix = new Matrix();
		matrix.setScale(scale, scale);
		this.setImageMatrix(matrix);
    }
	
}
