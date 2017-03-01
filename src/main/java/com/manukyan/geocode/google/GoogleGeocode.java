package com.manukyan.geocode.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class GoogleGeocode {

    private GoogleGeocodeResult[] results;

    public GoogleGeocode() {
    }

    public GoogleGeocodeResult[] getResults() {
        return results;
    }

    public void setResults(GoogleGeocodeResult[] results) {
        this.results = results;
    }
}
