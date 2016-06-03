package kz.jee7.cdi;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 14:02
 * Copyright © LLP JazzSoft
 */
@RunWith(Arquillian.class)
public class EconomyCreditProcessorTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(Economy.class, Premium.class, CreditProcessor.class,
                        HouseholdCredit.class, PremiumCreditProcessor.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    private @Inject @Economy CreditProcessor processor;

    @Test
    public void should_crate_greeting() {
        System.out.printf("processor = %s\n", processor);
        assertNotNull(processor);
        processor.check("1234");
    }
}
