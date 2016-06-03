package kz.jee7.cdi;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 13:44
 * Copyright © LLP JazzSoft
 */
public class HouseholdCredit {
    private static AtomicInteger counter = new AtomicInteger(1000);

    @Produces
    @Economy
    public CreditProcessor createCreditProcessor() {
        CreditProcessor processor = new StreetCreditProcessor("promoter" + counter.getAndIncrement());
        System.out.printf("#createCreditProcessor() " + "creates proc = %s\n", processor);
        return processor;
    }

    public void releaseCreditProcessor(@Disposes @Economy CreditProcessor processor) {
        System.out.printf("#releaseCreditProcessor() dispose proc = %s\n", processor);
    }

    public static class StreetCreditProcessor implements CreditProcessor {
        private final String workerId;
        public StreetCreditProcessor(String workerId) {
            this.workerId = workerId;
        }

        @Override
        public void check(String account) {
            System.out.println("check for account " + account);
        }

        @Override
        public String toString() {
            return "StreetCreditProcessor{" +
                    "workerId='" + workerId + '\'' +
                    '}';
        }
    }
}
