/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017. Shendy Aditya Syamsudin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package id.ac.akakom.bayu.wisatawonogiri.utils.slider.Transformers;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class BackgroundToForegroundTransformer extends BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		final float height = view.getHeight();
		final float width = view.getWidth();
		final float scale = min(position < 0 ? 1f : Math.abs(1f - position), 0.5f);

        ViewHelper.setScaleX(view,scale);
        ViewHelper.setScaleY(view,scale);
        ViewHelper.setPivotX(view,width*0.5f);
        ViewHelper.setPivotY(view,height*0.5f);
        ViewHelper.setTranslationX(view,position < 0 ? width * position : -width * position * 0.25f);
	}

	private static final float min(float val, float min) {
		return val < min ? min : val;
	}

}
