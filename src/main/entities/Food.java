package main.entities;

import java.math.BigDecimal;
import java.time.LocalTime;
import main.enums.FoodType;


public class Food {
    private int id;
    private String name;
    private FoodType type;
    private Long quantity;
    private BigDecimal price;
    private LocalTime preparationTime;

    public Food(){}

    public Food(final int id, final String name, final FoodType type, final Long quantity, final BigDecimal price,
            final LocalTime localTime) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.preparationTime = localTime;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(final FoodType type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public LocalTime getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(final LocalTime preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
