package com.manukyan.geocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class GeocodeController {

    @Autowired
    GeocodeService geocodeService;

    @Autowired
    CacheManager cacheManager;

    @RequestMapping("geocodes-last10")
    public Geocode[] getLast10Geocodes() {
        javax.cache.Cache cache = (javax.cache.Cache) cacheManager.getCache("geocodes").getNativeCache();
        List<Geocode> list = new ArrayList<>();
        Iterator<javax.cache.Cache.Entry> iterator = cache.iterator();
        iterator.forEachRemaining(entry -> {
            list.add((Geocode) entry.getValue());
        });
        return list.toArray(new Geocode[0]);
    }

    @RequestMapping("geocode-address/{latitude:.+}/{longitude:.+}")
    public String getGeocodeAddress(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude
    ) {
        return geocodeService.lookupGeocode(latitude, longitude).getAddress();
    }

    @RequestMapping("geocode/{latitude:.+}/{longitude:.+}")
    public Geocode getGeocode(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude
    ) {
        return geocodeService.lookupGeocode(latitude, longitude);
    }

}
