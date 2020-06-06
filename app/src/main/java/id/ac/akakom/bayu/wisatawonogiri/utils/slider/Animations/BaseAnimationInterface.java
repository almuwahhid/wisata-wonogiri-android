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

package id.ac.akakom.bayu.wisatawonogiri.utils.slider.Animations;

import android.view.View;

/**
 * This interface gives you chance to inject your own animation or do something when the
 * {@link com.gamatechno.slider.library.Tricks.ViewPagerEx} animation (PagerTransformer) starts or ends.
 *
 *
 * There are two items you have to know. The first item is the slider you are dragging. This item
 * I call it Current Item. The second is the slider that gonna to show. I call that Next Item.
 *
 * When you start to drag the slider in front of you, onPrepareCurrentItemLeaveScreen() and
 * onPrepareNextItemShowInScreen will be called.
 *
 * When you finish drag, the onCurrentItemDisappear and onNextItemAppear will be invoked.
 *
 * You can see a demo class {@link DescriptionAnimation},
 * this class gives the description text an animation.
 */
public interface BaseAnimationInterface {

    /**
     * When the current item prepare to start leaving the screen.
     * @param current
     */
    public void onPrepareCurrentItemLeaveScreen(View current);

    /**
     * The next item which will be shown in ViewPager/
     * @param next
     */
    public void onPrepareNextItemShowInScreen(View next);

    /**
     * Current item totally disappear from screen.
     * @param view
     */
    public void onCurrentItemDisappear(View view);

    /**
     * Next item totally show in screen.
     * @param view
     */
    public void onNextItemAppear(View view);
}
