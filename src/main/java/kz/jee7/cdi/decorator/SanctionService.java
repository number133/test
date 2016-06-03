package kz.jee7.cdi.decorator;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 10:15
 * Copyright © LLP JazzSoft
 */
public class SanctionService {
    public void sanction( String account, String ccyPair ) {
        System.out.printf("SanctionService#sanction (" +"account = %s, other = %s )\n", account, ccyPair);
    }
}
