package com.example.sheryarkhan.projectcity.Utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by Sheryar Khan on 9/20/2017.
 */

public class Constants {


    public static final LatLngBounds KARACHI_BOUNDS = new LatLngBounds(
            new LatLng(24.600851, 66.432167), new LatLng(25.676796,67.555412)); //SW,NE

    public static final LatLngBounds LAHORE_BOUNDS = new LatLngBounds(
            new LatLng(31.3489,73.9778), new LatLng(31.6902,74.6754)); //SW,NE

    public static final LatLngBounds ISLAMABAD_BOUNDS = new LatLngBounds(
            new LatLng(31.3489,73.9778), new LatLng(31.6902,74.6754)); //SW,NE

    public static final LatLngBounds QUETTA_BOUNDS = new LatLngBounds(
            new LatLng(33.5282,72.7158), new LatLng(33.8613,73.4134)); //SW,NE
}
