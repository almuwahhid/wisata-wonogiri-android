/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019. Al Muwahhid
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

package id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import id.ac.akakom.bayu.wisatawonogiri.R;

import static id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.Preconditions.checkNotNull;


/**
 * Represents the visual style of map marker icons. Supports customization
 * of individual attributes by setting their values with {@link Builder}.
 */
public class IconStyle {

    private final int clusterBackgroundColor;
    private final int clusterTextColor;
    private final int clusterStrokeColor;
    private final int clusterStrokeWidth;
    private final int clusterTextSize;
    private final int clusterIconResId;

    private IconStyle(@NonNull Builder builder) {
        clusterBackgroundColor = builder.clusterBackgroundColor;
        clusterTextColor = builder.clusterTextColor;
        clusterStrokeColor = builder.clusterStrokeColor;
        clusterStrokeWidth = builder.clusterStrokeWidth;
        clusterTextSize = builder.clusterTextSize;
        clusterIconResId = builder.clusterIconResId;
    }

    /**
     * Returns the background color of cluster icons.
     *
     * @return the background color of cluster icons
     */
    @ColorInt
    public int getClusterBackgroundColor() {
        return clusterBackgroundColor;
    }

    /**
     * Returns the text color of cluster icons.
     *
     * @return the text color of cluster icons
     */
    @ColorInt
    public int getClusterTextColor() {
        return clusterTextColor;
    }

    /**
     * Returns the text color of cluster icons.
     *
     * @return the text color of cluster icons
     */
    @ColorInt
    public int getClusterStrokeColor() {
        return clusterStrokeColor;
    }

    /**
     * Returns the width of the stroke of cluster icons.
     *
     * @return the width of the stroke of cluster icons
     */
    public int getClusterStrokeWidth() {
        return clusterStrokeWidth;
    }

    /**
     * Returns the size of the text of cluster icons.
     *
     * @return the size of the text of cluster icons
     */
    public int getClusterTextSize() {
        return clusterTextSize;
    }

    /**
     * Returns the icon resource of cluster items.
     *
     * @return the icon resource of cluster items
     */
    @DrawableRes
    public int getClusterIconResId() {
        return clusterIconResId;
    }

    /**
     * The builder for {@link IconStyle}. Allows to customize different style attributes.
     * If a style attribute is not set explicitly, the default value will be used.
     */
    public static class Builder {

        private int clusterBackgroundColor;
        private int clusterTextColor;
        private int clusterStrokeColor;
        private int clusterStrokeWidth;
        private int clusterTextSize;
        private int clusterIconResId;

        /**
         * Creates a new builder with the default style.
         */
        public Builder(@NonNull Context context) {
            checkNotNull(context);
            clusterBackgroundColor = ContextCompat.getColor(
                    context, R.color.primary);
            clusterTextColor = ContextCompat.getColor(
                    context, R.color.white);
            clusterStrokeColor = ContextCompat.getColor(
                    context, R.color.white);
            clusterStrokeWidth = context.getResources()
                    .getDimensionPixelSize(R.dimen.cluster_stroke_width);
            clusterTextSize = context.getResources()
                    .getDimensionPixelSize(R.dimen.cluster_text_size);
            clusterIconResId = R.drawable.ic_map_marker;
        }

        /**
         * Sets the background color of cluster icons.
         *
         * @param color the background color of cluster icons
         * @return the builder instance
         */
        public Builder setClusterBackgroundColor(@ColorInt int color) {
            clusterBackgroundColor = color;
            return this;
        }

        /**
         * Sets the text color of cluster icons.
         *
         * @param color the text color of cluster icons
         * @return the builder instance
         */
        public Builder setClusterTextColor(@ColorInt int color) {
            clusterTextColor = color;
            return this;
        }

        /**
         * Sets the color of the stroke of cluster icons.
         *
         * @param color the color of the stroke of cluster icons
         * @return the builder instance
         */
        public Builder setClusterStrokeColor(@ColorInt int color) {
            clusterStrokeColor = color;
            return this;
        }

        /**
         * Sets the width of the stroke of cluster icons.
         *
         * @param width the width of the stroke of cluster icons
         * @return the builder instance
         */
        public Builder setClusterStrokeWidth(int width) {
            clusterStrokeWidth = width;
            return this;
        }

        /**
         * Sets the size of the text of cluster icons.
         *
         * @param size the size of the text of cluster icons
         * @return the builder instance
         */
        public Builder setClusterTextSize(int size) {
            clusterTextSize = size;
            return this;
        }

        /**
         * Sets the icon resource of cluster items.
         *
         * @param resId the icon resource of cluster items
         * @return the builder instance
         */
        public Builder setClusterIconResId(@DrawableRes int resId) {
            clusterIconResId = resId;
            return this;
        }

        /**
         * Creates a new instance of {@link IconStyle} with provided style attributes.
         *
         * @return new instance of {@link IconStyle} with provided style attributes
         */
        @NonNull
        public IconStyle build() {
            return new IconStyle(this);
        }
    }
}
