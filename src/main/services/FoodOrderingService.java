package main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import main.compute.SearchStrategy;
import main.entities.Customer;
import main.entities.Food;
import main.entities.Location;
import main.entities.Order;
import main.entities.Restaurant;
import main.enums.GeoIndex;
import main.enums.Status;


public class FoodOrderingService {
    public Set<Location> locationList;
    public List<Restaurant> totalRestaurants = new ArrayList<>();
    public List<Order> totalOrderList = new ArrayList<>();

    private int orderId = 1;

    public FoodOrderingService() {
        Location locationA = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.A, new ArrayList<>());
        Location locationB = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.B, new ArrayList<>());
        Location locationC = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.C, new ArrayList<>());
        List<Location> locations = new ArrayList<>();
        locations.add(locationA);
        locations.add(locationB);
        locations.add(locationC);
        locationList = new HashSet<>(locations);
    }

    public List<Restaurant> onboardRestaurant(Location location, List<Food> foodList, Long capacity, String name)
            throws Exception {
        try {
            if (Objects.isNull(capacity) || capacity <= 0 || foodList.isEmpty() || Objects.isNull(location)) {
                throw new Exception("Invalid Input");
            }
            Restaurant restaurant = new Restaurant(foodList, location, name, capacity, null);
            locationList.stream().filter(location1 -> location1.getGeoIndex().equals(location.getGeoIndex()))
                    .findFirst().get().getRestaurantList()
                    .add(restaurant);
            totalRestaurants.add(restaurant);
            return totalRestaurants;
        } catch (Exception ex) {
            throw new Exception("Error onboarding : " + ex.getMessage());
        }

    }

    public void changeMenuItems(Restaurant restaurant, List<Food> foodList) throws Exception {
        try {
            List<Food> foods = restaurant.getMenu();
            List<Food> finalFoods = new ArrayList<>();
            for (Food food : foods) {
                final Food finalFood = food;
                Optional<Food> food1 = foodList.stream().filter(food2 -> food2.getId() == finalFood.getId())
                        .findFirst();
                if (food1.isPresent()) {
                    Food newFood = food1.get();
                    food.setName(newFood.getName());
                    food.setPrice(newFood.getPrice());
                    food.setQuantity(newFood.getQuantity());
                }
                finalFoods.add(food);
            }
            restaurant.setMenu(finalFoods);
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    public List<Food> getMenu(Restaurant restaurant) throws Exception {
        try {
            Optional<Restaurant> restaurantOptional =
                    totalRestaurants.stream().filter(restaurant1 -> restaurant1.equals(restaurant)).findFirst();
            if (restaurantOptional.isPresent()) {
                return restaurantOptional.get().getMenu();
            } else {
                throw new Exception("Restaurant Not Found");
            }
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    public void placeOrder(List<Food> foodList, Customer customer, Restaurant restaurant) throws Exception {
        try {
            Order order = new Order();
            order.setOrderId(orderId++);
            order.setFoodList(foodList);
            order.setCustomer(customer);
            order.setStatus(Status.CREATED);
            order.setRestaurant(restaurant);
            totalOrderList.add(order);


            long capacity = restaurant.getCapacity();
            if (capacity == 0) {
                throw new Exception("Can't take order, capacity exceeded.");
            } else {
                restaurant.setCapacity(capacity - 1);
            }

        } catch (Exception ex) {
            throw new Exception("Placing order failed");
        }

    }

    public void orderDelivered(Order order) throws Exception {
        try {
            order.setStatus(Status.DELIVERED);
            order.getRestaurant().setCapacity(order.getRestaurant().getCapacity() + 1);
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    public void searchFood(Food food, SearchStrategy strategy) {
        try {

        } catch (Exception e) {

        }
    }

}


//
//SQL transactionLog -> transactionLog will acquire the row locks on the data which being accessed.
//        Kafka
//servicaA ->
//serviceB ->