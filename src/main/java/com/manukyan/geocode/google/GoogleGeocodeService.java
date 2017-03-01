package com.manukyan.geocode.google;

import com.manukyan.geocode.Geocode;
import com.manukyan.geocode.GeocodeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class GoogleGeocodeService implements GeocodeService {

    @Override
    @Cacheable("geocodes")
    public Geocode lookupGeocode(double latitude, double longitude) {
        return new Geocode(
                latitude,
                longitude,
                lookupGeocodeAddress(latitude, longitude),
                LocalDateTime.now());
    }

    public String lookupGeocodeAddress(double latitude, double longitude) {
        GoogleGeocode googleGeocode = getGoogleGeocode(latitude, longitude);
        if (googleGeocode.getResults().length == 0) {
            return "No address returned";
        }
        return googleGeocode.getResults()[0].getFormattedAddress();
    }

    public GoogleGeocode getGoogleGeocode(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrl(latitude, longitude), GoogleGeocode.class);
    }

    public String getUrl(double latitude, double longitude) {
        return "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude;
    }

}
