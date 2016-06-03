package kz.jee7.cdi.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 11:13
 * Copyright © LLP JazzSoft
 */
public class CreditApprovalPostProcess {
//    @Inject
//    ExternalServices externalServices;

    public void postApproval
            (@Observes ApplicationApproved application) {
//        externalServices.process(application);
    }
}
