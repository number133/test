package kz.jee7.ejb;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 13:49
 * Copyright © LLP JazzSoft
 */
@Local
public interface ShoppingCartLocal {
    void initialize(Customer customer);
    void addOrderItem(OrderItem item);
    void removeOrderItem(OrderItem item);
    List<OrderItem> getOrderItems();
    void release();
}
