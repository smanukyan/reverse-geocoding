package com.manukyan.geocode.google;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GoogleGeocodeServiceTest {

    @Test
    public void getGeocode() throws Exception {
        GoogleGeocodeService geocodeService = new GoogleGeocodeService();
        Geocode geocode = geocodeService.getGeocode(33.969601, -84.100033);
        assertNotNull(geocode);
        GeocodeResult[] results = geocode.getResults();
        assertNotNull(results);
        assertTrue(results.length > 0);
    }

    @Test
    public void getUrl() throws Exception {
        GoogleGeocodeService geocodeService = new GoogleGeocodeService();
        assertEquals(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=33.969601,-84.100033",
                geocodeService.getUrl(33.969601, -84.100033));
    }

    @Test
    public void getAddressFromGeocode() throws Exception {
        GoogleGeocodeService geocodeService = new GoogleGeocodeService();
        assertEquals(
                "2651 Satellite Blvd, Duluth, GA 30096, USA",
                geocodeService.getAddressFromGeocode(33.969601, -84.100033));
    }

}