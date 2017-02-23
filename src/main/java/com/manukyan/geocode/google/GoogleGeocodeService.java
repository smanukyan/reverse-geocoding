package com.manukyan.geocode.google;

import com.manukyan.geocode.GeocodeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleGeocodeService implements GeocodeService {

    @Override
    public String getAddressFromGeocode(double latitude, double longitude) {
        Geocode geocode = getGeocode(latitude, longitude);
        if (geocode.getResults().length == 0) {
            return "No address returned";
        }
        return geocode.getResults()[0].getFormattedAddress();
    }

    public Geocode getGeocode(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrl(latitude, longitude), Geocode.class);
    }

    public String getUrl(double latitude, double longitude) {
        return "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude;
    }

}
