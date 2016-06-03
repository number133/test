package kz.jee7.cdi;

import kz.jee7.cdi.interceptor.TransactionalBound;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Default;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 15:55
 * Copyright © LLP JazzSoft
 */
@Default
public class TransactionalCreditProcessor implements CreditProcessor{
    @Override
    @TransactionalBound
    public void check(String account) {
        if (!account.trim().startsWith("1234")) {
            throw new RuntimeException
                    ("account:["+account+"] is not valid!");
        }
        System.out.printf("Inside Transactional Account [%s]"
                + "is Okay\n", account);
    }

    @PostConstruct
    public void acquireResource() {
        System.out.println( this.getClass()
                .getSimpleName()+"#acquireResource()");
    }

    @PreDestroy
    public void releaseResource() {
        System.out.println( this.getClass()
                .getSimpleName()+"#releaseResource()" );
    }
}
