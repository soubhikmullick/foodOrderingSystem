package main.entities;

import java.util.List;
import main.enums.Rating;


public class Restaurant {
    private List<Food> menu;
    private Location location;
    private String name;
    private Long capacity;
    private Rating rating;

    public Restaurant(final List<Food> menu, final Location location, final String name, final Long capacity,
            final Rating rating) {
        this.menu = menu;
        this.location = location;
        this.name = name;
        this.capacity = capacity;
        this.rating = rating;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public void setMenu(final List<Food> menu) {
        this.menu = menu;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(final Long capacity) {
        this.capacity = capacity;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(final Rating rating) {
        this.rating = rating;
    }
}
