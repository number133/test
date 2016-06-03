package kz.jee7.ejb;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 14:00
 * Copyright © LLP JazzSoft
 */
@Stateful
public class ShoppingCartBean implements ShoppingCart, ShoppingCartLocal{
    private List<OrderItem> orderItems =
            new ArrayList<OrderItem>();
    private Customer customer = null;
    private boolean initialized = false;

    @Override
    public void initialize(Customer customer) {
        System.out.printf(
                "SCB#initialize() called%s\n", this );
        this.customer = customer;
        this.initialized = true;
    }

    protected void check() {
        if ( !initialized ) {
            throw new RuntimeException(
                    "This shopping cart is not initialised");
        }
    }

    @Override
    public void addOrderItem(OrderItem item) {
        System.out.printf("SCB#addOrderItem() called%s\n", item );
        check();
        orderItems.add( item );
    }

    @Override
    public void removeOrderItem(OrderItem item) {
        System.out.printf("removeOrderItem() called%s\n", this);
        check();
        orderItems.remove( item );
    }

    @Override
    public List<OrderItem> getOrderItems() {
        check();
        return orderItems;
    }

    @Remove
    public void release() {
        System.out.printf("SCB#release() called%s\n", this );
        orderItems.clear();
        customer = null;
    }
}
