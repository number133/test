package kz.jee7.ejb.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.util.Properties;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 17:28
 * Copyright © LLP JazzSoft
 */
@Singleton
public class ConductorBean implements Conductor {
    private final Properties properties = new Properties();

    @Override
    public void orchestrate(String data) {
        System.out.printf("ConductorBean#orchestrate( %s ) %s\n",
                data, this );
    }
    @PostConstruct
    public void appStartUp() {
        properties.putAll(System.getProperties());
        System.out.printf("ConductorBean#init() %s\n" +
                        "java.version=%s\n", this,
                properties.get("java.version") );
    }
    @PreDestroy
    public void appShutDown() {
        System.out.printf("ConductorBean#shutdown() %s\n", this );
        properties.clear();
    }
}
