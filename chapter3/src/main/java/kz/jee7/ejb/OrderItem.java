package kz.jee7.ejb;

import java.io.Serializable;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 13:57
 * Copyright © LLP JazzSoft
 */
public class OrderItem implements Serializable {
    private final int quantity;
    private final Product product;
    public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product;}
    // equals(), hashCode() and toString() ommitted
}
