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

package id.ac.akakom.bayu.wisatawonogiri.utils.slider.SliderTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.ac.akakom.bayu.wisatawonogiri.R;


/**
 * This is a slider with a description TextView.
 */
public class ImageSliderView extends BaseSliderView{
    public ImageSliderView(Context context, String url) {
        super(context, url);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.slider_layout_custom,null);
        ImageView img_slide = (ImageView)v.findViewById(R.id.img_slide);
//        img_slide.setImageDrawable(getContext().getResources().getDrawable(getImg_drawable()));
        Picasso.with(getContext())
                .load(getImg_url())
                .error(R.drawable.ic_logo)
                .into(img_slide);
        return v;
    }
}
