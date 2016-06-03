package kz.jee7.ejb;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 12:23
 * Copyright © LLP JazzSoft
 */
@RunWith(Arquillian.class)
public class SupportHelpDeskTest {
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(SupportHelpDesk.class)
                .addAsManifestResource(
                        EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
        return jar;
    }

    @EJB
    SupportHelpDesk desk;

    @Test
    public void shouldRetrieveDifferentAgents() {
        System.out.printf("Support help desk = %s\n", desk );
        for ( int j=0; j<5; ++j ) {
            String agent = desk.getNextAgentName();
            System.out.printf("The next agent = %s\n",agent);
            assertNotNull(agent);
        }
    }
}
