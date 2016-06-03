package kz.jee7.ejb;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 14:12
 * Copyright © LLP JazzSoft
 */
public class Product implements Serializable {
    private final int id;
    private final String name,description;
    private final BigDecimal price;
    public Product(int id, String name,
                   String description, BigDecimal price ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public int getId() {return id; }
    public String getName() { return name; }
    public String getDescription() {
        return description; }
    public BigDecimal getPrice() {
        return new BigDecimal(price.doubleValue());
    }
    // equals(), hashCode() and toString() ommitted
}