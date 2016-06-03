package kz.jee7.cdi.event;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 11:11
 * Copyright © LLP JazzSoft
 */
public class ApprovalNotifier {
    @Inject
    Event<ApplicationApproved> eventSource;

    public void fireEvents(String msg) {
        eventSource.fire(new ApplicationApproved(msg));
    }
}
