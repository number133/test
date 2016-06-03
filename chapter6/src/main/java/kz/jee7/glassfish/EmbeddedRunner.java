package kz.jee7.glassfish;

import org.glassfish.embeddable.GlassFish;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 12.05.2016 12:00
 * Copyright © LLP JazzSoft
 */
public class EmbeddedRunner {
    private int port;
    private AtomicBoolean initialized = new AtomicBoolean();
    private GlassFish glassfish;
}
