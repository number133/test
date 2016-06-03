package kz.jee7.ejb.singleton;

import javax.ejb.Remote;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 17:28
 * Copyright © LLP JazzSoft
 */
@Remote
public interface Conductor {
    public void orchestrate( String data );
}
