package kz.jee7.cdi;

import kz.jee7.cdi.interceptor.TransactionalBound;
import kz.jee7.cdi.interceptor.TransactionalBoundInterceptor;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 15:58
 * Copyright © LLP JazzSoft
 */
@RunWith(Arquillian.class)
public class TransactionalCreditProcessorTest {
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(Economy.class, Premium.class,
                        CreditProcessor.class, TransactionalBound.class,
                        TransactionalCreditProcessor.class,
                        TransactionalBoundInterceptor.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

    private @Inject
    CreditProcessor processor;

    @Test
    public void shouldProcessTransactionalCredit() {
        System.out.printf("processor = %s\n", processor );
        assertNotNull(processor);
        processor.check("1234");
    }
}
