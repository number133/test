package kz.jee7.ejb.singleton;

import kz.jee7.ejb.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 17:30
 * Copyright © LLP JazzSoft
 */
@RunWith(Arquillian.class)
public class ConductorBeanTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(Conductor.class, ConductorBean.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
        return jar;
    }
    @EJB Conductor conductor1;
    @EJB Conductor conductor2;
    @EJB Conductor conductor3;

    @Test
    public void shouldInjectSingletonEJB() {
        System.out.printf("conductor1 = %s\n", conductor1 );
        conductor1.orchestrate("conductor1");
        System.out.printf("conductor2 = %s\n", conductor2 );
        conductor1.orchestrate("conductor2");
        System.out.printf("conductor3 = %s\n", conductor3 );
        conductor1.orchestrate("conductor3");
        assertEquals( conductor2, conductor1 );
        assertEquals( conductor2, conductor3 );
        assertEquals( conductor1, conductor3 );
    }
}
