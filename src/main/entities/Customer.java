package main.entities;

import java.util.List;


public class Customer {
    private String name;
    private Location location;
    private List<Order> orders;
    private List<Restaurant> favoriteRestaurants;

    public Customer(final String name, final Location location, final List<Order> orders,
            final List<Restaurant> favoriteRestaurants) {
        this.name = name;
        this.location = location;
        this.favoriteRestaurants = favoriteRestaurants;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    public List<Restaurant> getFavoriteRestaurants() {
        return favoriteRestaurants;
    }

    public void setFavoriteRestaurants(final List<Restaurant> favoriteRestaurants) {
        this.favoriteRestaurants = favoriteRestaurants;
    }




}
