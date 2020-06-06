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

import android.os.Build;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import id.ac.akakom.bayu.wisatawonogiri.utils.slider.Tricks.ViewPagerEx;


public class FlipPageViewTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        float percentage = 1 - Math.abs(position);
        if(Build.VERSION.SDK_INT >= 13){
            view.setCameraDistance(12000);
        }
        setVisibility(view, position);
        setTranslation(view);
        setSize(view, position, percentage);
        setRotation(view, position, percentage);
    }

    private void setVisibility(View page, float position) {
        if (position < 0.5 && position > -0.5) {
            page.setVisibility(View.VISIBLE);
        } else {
            page.setVisibility(View.INVISIBLE);
        }
    }

    private void setTranslation(View view) {
        ViewPagerEx viewPager = (ViewPagerEx) view.getParent();
        int scroll = viewPager.getScrollX() - view.getLeft();
        ViewHelper.setTranslationX(view,scroll);
    }

    private void setSize(View view, float position, float percentage) {
        ViewHelper.setScaleX(view,(position != 0 && position != 1) ? percentage : 1);
        ViewHelper.setScaleY(view,(position != 0 && position != 1) ? percentage : 1);
    }

    private void setRotation(View view, float position, float percentage) {
        if (position > 0) {
            ViewHelper.setRotationY(view,-180 * (percentage + 1));
        } else {
            ViewHelper.setRotationY(view,180 * (percentage + 1));
        }
    }
}