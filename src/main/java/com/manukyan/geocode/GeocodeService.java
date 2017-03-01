package com.manukyan.geocode;

public interface GeocodeService {

    Geocode lookupGeocode(double latitude, double longitude);
}
