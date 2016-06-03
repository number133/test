package kz.jee7.cdi;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 14:17
 * Copyright © LLP JazzSoft
 */
@Premium
public class PremiumCreditProcessor implements CreditProcessor {
    @Override
    public void check(String account) {
        if (!account.trim().startsWith("1234")) {
            throw new RuntimeException("account:["+account+"] is not valid!");
        }
    }

    @PostConstruct
    public void acquireResource() {
        System.out.println( this.getClass()
                .getSimpleName()+"#acquireResource()");
    }

    @PreDestroy
    public void releaseResource() {
        System.out.println( this.getClass()
                .getSimpleName()+"#releaseResource()");
    }
}

