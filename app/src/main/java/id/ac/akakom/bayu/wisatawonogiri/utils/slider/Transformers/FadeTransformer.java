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

/**
 * Created by realandylawton on 11/22/13.
 */
public class FadeTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {

        // Page is not an immediate sibling, just make transparent
        if(position < -1 || position > 1) {
            ViewHelper.setAlpha(view,0.6f);
        }
        // Page is sibling to left or right
        else if (position <= 0 || position <= 1) {

            // Calculate alpha.  Position is decimal in [-1,0] or [0,1]
            float alpha = (position <= 0) ? position + 1 : 1 - position;
            ViewHelper.setAlpha(view,alpha);

        }
        // Page is active, make fully visible
        else if (position == 0) {
            ViewHelper.setAlpha(view,1);
        }
    }

}