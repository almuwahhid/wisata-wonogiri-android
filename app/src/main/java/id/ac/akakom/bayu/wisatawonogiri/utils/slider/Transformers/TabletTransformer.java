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

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class TabletTransformer extends BaseTransformer {

	private static final Matrix OFFSET_MATRIX = new Matrix();
	private static final Camera OFFSET_CAMERA = new Camera();
	private static final float[] OFFSET_TEMP_FLOAT = new float[2];

	@Override
	protected void onTransform(View view, float position) {
		final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

		ViewHelper.setTranslationX(view,getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view,0);
        ViewHelper.setRotationY(view,rotation);
	}

	protected static final float getOffsetXForRotation(float degrees, int width, int height) {
		OFFSET_MATRIX.reset();
		OFFSET_CAMERA.save();
		OFFSET_CAMERA.rotateY(Math.abs(degrees));
		OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
		OFFSET_CAMERA.restore();

		OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
		OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
		OFFSET_TEMP_FLOAT[0] = width;
		OFFSET_TEMP_FLOAT[1] = height;
		OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
		return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
	}

}
