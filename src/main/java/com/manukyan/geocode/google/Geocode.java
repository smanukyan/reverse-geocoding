package com.manukyan.geocode.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Geocode {

    private GeocodeResult[] results;

    public Geocode() {
    }

    public GeocodeResult[] getResults() {
        return results;
    }

    public void setResults(GeocodeResult[] results) {
        this.results = results;
    }
}
