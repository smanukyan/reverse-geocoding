package com.manukyan.geocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodeController {

    @Autowired
    GeocodeService geocodeService;

    @RequestMapping("geocode/{latitude}/{longitude}")
    @Cacheable("geocodes")
    public String getAddressFromGeocode(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude
    ) {
        return geocodeService.getAddressFromGeocode(latitude, longitude);
    }
}
