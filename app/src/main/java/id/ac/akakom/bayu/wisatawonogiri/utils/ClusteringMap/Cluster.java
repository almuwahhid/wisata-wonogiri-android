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

import java.util.List;

/**
 * An object representing a cluster of items (markers) on the map.
 */
public class Cluster<T extends ClusterItem> {

    private final double latitude;
    private final double longitude;
    private final List<T> items;
    private final double north;
    private final double west;
    private final double south;
    private final double east;

    Cluster(double latitude, double longitude, @NonNull List<T> items,
            double north, double west, double south, double east) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.items = items;
        this.north = north;
        this.west = west;
        this.south = south;
        this.east = east;
    }

    /**
     * The latitude of the cluster.
     *
     * @return the latitude of the cluster
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * The longitude of the cluster.
     *
     * @return the longitude of the cluster
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The items contained in the cluster.
     *
     * @return the items contained in the cluster
     */
    @NonNull
    public List<T> getItems() {
        return items;
    }

    boolean contains(double latitude, double longitude) {
        return longitude >= west && longitude <= east
                && latitude <= north && latitude >= south;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cluster cluster = (Cluster) o;
        return Double.compare(cluster.latitude, latitude) == 0 &&
                Double.compare(cluster.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
