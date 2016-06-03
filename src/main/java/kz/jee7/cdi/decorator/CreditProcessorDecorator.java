package kz.jee7.cdi.decorator;

import kz.jee7.cdi.CreditProcessor;
import kz.jee7.cdi.Premium;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 10:16
 * Copyright © LLP JazzSoft
 */
@Decorator
@Premium
public class CreditProcessorDecorator implements CreditProcessor {

    @Inject
    SanctionService sanctionService;
    @Inject @Delegate @Premium
    CreditProcessor processor;

    @Override
    public void check(String account) {
        sanctionService.sanction(account, "EURGBP");
        processor.check(account);
    }
}
