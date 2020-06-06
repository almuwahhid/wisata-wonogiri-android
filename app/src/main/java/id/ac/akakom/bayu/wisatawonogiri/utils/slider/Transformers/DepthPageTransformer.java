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

public class DepthPageTransformer extends BaseTransformer {

	private static final float MIN_SCALE = 0.75f;

	@Override
	protected void onTransform(View view, float position) {
		if (position <= 0f) {
            ViewHelper.setTranslationX(view,0f);
            ViewHelper.setScaleX(view,1f);
            ViewHelper.setScaleY(view,1f);
		} else if (position <= 1f) {
			final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            ViewHelper.setAlpha(view,1-position);
            ViewHelper.setPivotY(view,0.5f * view.getHeight());
            ViewHelper.setTranslationX(view,view.getWidth() * - position);
            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);
		}
	}

	@Override
	protected boolean isPagingEnabled() {
		return true;
	}

}
