package com.manukyan.geocode.google;

import com.manukyan.geocode.Geocode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoogleGeocodeServiceTest {

    private GoogleGeocodeService googleGeocodeService;

    @Before
    public void setUp() throws Exception {
        this.googleGeocodeService = new GoogleGeocodeService();
    }

    @Test
    public void getGoogleGeocode() throws Exception {
        GoogleGeocode googleGeocode = googleGeocodeService.getGoogleGeocode(33.969601, -84.100033);
        assertNotNull(googleGeocode);
        GoogleGeocodeResult[] results = googleGeocode.getResults();
        assertNotNull(results);
        assertTrue(results.length > 0);
    }

    @Test
    public void getUrl() throws Exception {
        assertEquals(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=33.969601,-84.100033",
                googleGeocodeService.getUrl(33.969601, -84.100033));
    }

    @Test
    public void lookupGeocodeAddress() throws Exception {
        String geocodeAddress = googleGeocodeService.lookupGeocodeAddress(33.969601, -84.100033);
        assertEquals("2651 Satellite Blvd, Duluth, GA 30096, USA", geocodeAddress);
    }

    @Test
    public void lookupGeocode() throws Exception {
        Geocode geocode = googleGeocodeService.lookupGeocode(33.969601, -84.100033);
        assertNotNull(geocode);
        assertEquals("2651 Satellite Blvd, Duluth, GA 30096, USA", geocode.getAddress());
        assertEquals(33.969601, geocode.getLatitude(), 0);
        assertEquals(-84.100033, geocode.getLongitude(), 0);
        assertNotNull(geocode.getDate());
    }
}