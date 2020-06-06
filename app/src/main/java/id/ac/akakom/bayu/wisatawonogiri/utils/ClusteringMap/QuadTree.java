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

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

class QuadTree<T extends QuadTreePoint> {

    private final int bucketSize;

    private QuadTreeNode<T> root;

    QuadTree(int bucketSize) {
        this.bucketSize = bucketSize;
        this.root = createRootNode(bucketSize);
    }

    void insert(@NonNull T point) {
        root.insert(point);
    }

    @NonNull
    List<T> queryRange(double north, double west, double south, double east) {
        List<T> points = new ArrayList<>();
        root.queryRange(new QuadTreeRect(north, west, south, east), points);
        return points;
    }

    void clear() {
        root = createRootNode(bucketSize);
    }

    @NonNull
    private QuadTreeNode<T> createRootNode(int bucketSize) {
        return new QuadTreeNode<>(90.0, -180.0, -90.0, 180.0, bucketSize);
    }
}
