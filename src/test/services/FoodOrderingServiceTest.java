package test.services;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.entities.Customer;
import main.entities.Food;
import main.entities.Location;
import main.entities.Restaurant;
import main.enums.FoodType;
import main.entities.Order;
import main.enums.GeoIndex;
import main.enums.Status;
import main.services.FoodOrderingService;


public class FoodOrderingServiceTest {

    private FoodOrderingService foodOrderingService = new FoodOrderingService();
    Location locationA = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.A, new ArrayList<>());
    Location locationB = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.B, new ArrayList<>());
    Location locationC = new Location("Karnataka", "Bangalore", "Temple", GeoIndex.C, new ArrayList<>());

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onboardRestaurant_Success() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);

        try {
            List<Restaurant> restaurants = foodOrderingService.onboardRestaurant(locationA, menu, 20L, "A2B");
            assert restaurants.stream().filter(restaurant1 -> restaurant1.getName().equals(restaurant.getName())).findAny().isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void onboardRestaurant_Failure() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);

        try {
            List<Restaurant> restaurants = foodOrderingService.onboardRestaurant(locationA, menu, null, "A2B");
            assert restaurants.stream().filter(restaurant1 -> restaurant1.getName().equals(restaurant.getName())).findAny().isPresent();
        } catch (Exception e) {
            assert e.getMessage().equals("Error onboarding : Invalid Input");
        }
    }

    @Test
    public void changeMenuItems() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);

        List<Food> newMenu = new ArrayList<>();
        newMenu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("19"), LocalTime.of(0, 20)));
        newMenu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("20"), LocalTime.of(0, 20)));

        try {
            foodOrderingService.changeMenuItems(restaurant, newMenu);
            assert restaurant.getMenu().stream().filter(food -> food.getId()==1).findFirst().get().getPrice().equals(new BigDecimal(
                    "19"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getMenu() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);
        try {
            foodOrderingService.totalRestaurants.add(restaurant);
            List<Food> foods = foodOrderingService.getMenu(restaurant);
            assert foods.stream()
                    .noneMatch(food -> menu.stream().filter(existingFood -> existingFood.getId() != food.getId())
                            .isParallel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void placeOrder() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);
        Customer customer = new Customer("A", locationA, null, null);
        try {
            foodOrderingService.placeOrder(menu, customer, restaurant);
            assert restaurant.getCapacity().equals(19L);
            assert foodOrderingService.totalOrderList.stream().filter(order -> order.getStatus().equals(Status.CREATED)).count()==1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void orderDelivered() {
        List<Food> menu = new ArrayList<>();
        menu.add(new Food(1,"Samosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(2,"Vada", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(3,"Dosa", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));
        menu.add(new Food(4,"Idli", FoodType.Savory, 20L, new BigDecimal("45.5"), LocalTime.of(0, 20)));

        Restaurant restaurant = new Restaurant(menu, locationA, "A2B", 20L, null);
        Customer customer = new Customer("A", locationA, null, null);
        try {
            foodOrderingService.placeOrder(menu, customer, restaurant);
            Order order = foodOrderingService.totalOrderList.get(0);
            foodOrderingService.orderDelivered(order);
            assert order.getStatus().equals(Status.DELIVERED);
            assert restaurant.getCapacity().equals(20L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchFood() {
    }
}