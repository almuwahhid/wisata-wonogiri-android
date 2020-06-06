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

class QuadTreeNode<T extends QuadTreePoint> {

    private final QuadTreeRect bounds;
    private final List<T> points;
    private final int bucketSize;
    private QuadTreeNode<T> northWest;
    private QuadTreeNode<T> northEast;
    private QuadTreeNode<T> southWest;
    private QuadTreeNode<T> southEast;

    QuadTreeNode(double north, double west, double south, double east, int bucketSize) {
        this.bounds = new QuadTreeRect(north, west, south, east);
        this.points = new ArrayList<>(bucketSize);
        this.bucketSize = bucketSize;
    }

    boolean insert(@NonNull T point) {
        // Ignore objects that do not belong in this quad tree.
        if (!bounds.contains(point.getLatitude(), point.getLongitude())) {
            return false;
        }

        // If there is space in this quad tree, add the object here.
        if (points.size() < bucketSize) {
            points.add(point);
            return true;
        }

        // Otherwise, subdivide and then add the point to whichever node will accept it.
        if (northWest == null) {
            subdivide();
        }

        if (northWest.insert(point)) {
            return true;
        }
        if (northEast.insert(point)) {
            return true;
        }
        if (southWest.insert(point)) {
            return true;
        }
        if (southEast.insert(point)) {
            return true;
        }

        // Otherwise, the point cannot be inserted for some unknown reason (this should never happen).
        return false;
    }

    void queryRange(@NonNull QuadTreeRect range, @NonNull List<T> pointsInRange) {
        // Automatically abort if the range does not intersect this quad.
        if (!bounds.intersects(range)) {
            return;
        }

        // Check objects at this quad level.
        for (T point : points) {
            if (range.contains(point.getLatitude(), point.getLongitude())) {
                pointsInRange.add(point);
            }
        }

        // Terminate here, if there are no children.
        if (northWest == null) {
            return;
        }

        // Otherwise, add the points from the children.
        northWest.queryRange(range, pointsInRange);
        northEast.queryRange(range, pointsInRange);
        southWest.queryRange(range, pointsInRange);
        southEast.queryRange(range, pointsInRange);
    }

    private void subdivide() {
        double northSouthHalf = bounds.north - (bounds.north - bounds.south) / 2.0;
        double eastWestHalf = bounds.east - (bounds.east - bounds.west) / 2.0;

        northWest = new QuadTreeNode<>(bounds.north, bounds.west, northSouthHalf, eastWestHalf, bucketSize);
        northEast = new QuadTreeNode<>(bounds.north, eastWestHalf, northSouthHalf, bounds.east, bucketSize);
        southWest = new QuadTreeNode<>(northSouthHalf, bounds.west, bounds.south, eastWestHalf, bucketSize);
        southEast = new QuadTreeNode<>(northSouthHalf, eastWestHalf, bounds.south, bounds.east, bucketSize);
    }
}
