package kz.jee7.ejb;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 14:13
 * Copyright © LLP JazzSoft
 */
@RunWith(Arquillian.class)
public class ShoppingCartBeanTest {
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(Customer.class, OrderItem.class, Product.class, ShoppingCart.class,
                        ShoppingCartLocal.class, ShoppingCartBean.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
        return jar;
    }

    Product p1 = new Product(
            1000, "IWG", "Iron Widget Grayson",
            new BigDecimal("4.99" ));
    Product p2 = new Product(
            1002, "MSB", "Miller Steel Bolt",
            new BigDecimal("8.99" ));
    Product p3 = new Product(
            1004, "ASCC", "Alphason Carbonite",
            new BigDecimal("15.99" ));
    Customer customer = new Customer("Fred","Other");

    @EJB
    ShoppingCartLocal cart;
    void dumpCart( List<OrderItem> items ) { /* ... */ }

    @Test
    public void shouldAddItemsToCart() {
        System.out.printf("cart = %s\n", cart);
        assertNotNull(cart);
        cart.initialize(customer);

        System.out.printf("Initial state of the cart\n");
        dumpCart( cart.getOrderItems());
        OrderItem item1 = new OrderItem( 4, p1 );
        cart.addOrderItem( item1 );
        assertEquals( 1, cart.getOrderItems().size() );
        System.out.printf("After adding one item\n");
        dumpCart( cart.getOrderItems());
        OrderItem item2 = new OrderItem( 7, p2 );
        cart.addOrderItem( item2 );
        assertEquals(2, cart.getOrderItems().size());
        System.out.printf("After adding two items");
        dumpCart( cart.getOrderItems());
        OrderItem item3 = new OrderItem( 10, p3 );
        cart.addOrderItem( item3 );
        assertEquals(3, cart.getOrderItems().size());
        System.out.printf("After adding three items\n");
        dumpCart( cart.getOrderItems());

        cart.release();
    }
}
