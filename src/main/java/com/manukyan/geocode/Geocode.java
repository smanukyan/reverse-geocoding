package com.manukyan.geocode;

import java.time.LocalDateTime;

public class Geocode {

    double longitude;
    double latitude;
    String address;
    LocalDateTime date;

    public Geocode(double latitude, double longitude, String address, LocalDateTime date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geocode that = (Geocode) o;

        if (Double.compare(that.longitude, longitude) != 0) return false;
        return Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(longitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Geocode{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", address='" + address + '\'' +
                ", date=" + date +
                '}';
    }
}
