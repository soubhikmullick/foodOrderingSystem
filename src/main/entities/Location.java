package main.entities;

import java.util.List;
import main.enums.GeoIndex;


public class Location {
    private String state;
    private String city;
    private String landmark;
    private GeoIndex geoIndex; //todo
    private List<Restaurant> restaurantList;

    public Location(final String state, final String city, final String landmark, final GeoIndex geoIndex,
            final List<Restaurant> restaurantList) {
        this.state = state;
        this.city = city;
        this.landmark = landmark;
        this.geoIndex = geoIndex;
        this.restaurantList = restaurantList;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(final String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(final List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public GeoIndex getGeoIndex() {
        return geoIndex;
    }

    public void setGeoIndex(final GeoIndex geoIndex) {
        this.geoIndex = geoIndex;
    }
}
