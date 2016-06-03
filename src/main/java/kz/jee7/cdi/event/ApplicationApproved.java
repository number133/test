package kz.jee7.cdi.event;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 11:11
 * Copyright © LLP JazzSoft
 */
public class ApplicationApproved {
    private final String message;

    public ApplicationApproved(String message) {
        this.message = message;
    }
    public String getMessage() {return message;}
}
