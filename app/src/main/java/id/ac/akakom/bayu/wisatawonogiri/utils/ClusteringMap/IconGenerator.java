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

import com.google.android.gms.maps.model.BitmapDescriptor;

/**
 * Generates icons for clusters and cluster items. Note that its implementations
 * should cache generated icons for subsequent use. For the example implementation see
 * {@link }.
 */
public interface IconGenerator<T extends ClusterItem> {
    /**
     * Returns an icon for the given cluster.
     *
     * @param cluster the cluster to return an icon for
     * @return the icon for the given cluster
     */
    @NonNull
    BitmapDescriptor getClusterIcon(@NonNull Cluster<T> cluster);

    /**
     * Returns an icon for the given cluster item.
     *
     * @param clusterItem the cluster item to return an icon for
     * @return the icon for the given cluster item
     */
    @NonNull
    BitmapDescriptor getClusterItemIcon(@NonNull T clusterItem);
}
