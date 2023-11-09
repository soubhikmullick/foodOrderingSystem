package main.entities;

import java.util.List;
import main.enums.Status;


public class Order {
    private long orderId;
    private List<Food> foodList;
    private Restaurant restaurant;
    private Status status;
    private Customer customer;

    public Order(final long orderId, final List<Food> foodList, final Restaurant restaurant, final Status status,
            final Customer customer) {
        this.orderId = orderId;
        this.foodList = foodList;
        this.restaurant = restaurant;
        this.status = status;
        this.customer = customer;
    }



    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(final List<Food> foodList) {
        this.foodList = foodList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
