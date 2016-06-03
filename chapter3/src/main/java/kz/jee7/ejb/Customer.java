package kz.jee7.ejb;

import java.io.Serializable;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 13:42
 * Copyright © LLP JazzSoft
 */
public class Customer implements Serializable {
    private final String firstName;
    private final String lastName;
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName;}

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
