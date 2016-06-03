package kz.jee7.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.05.2016 12:00
 * Copyright © LLP JazzSoft
 */
@Stateless
@LocalBean
public class SupportHelpDesk {

    private List<String> agents = Arrays.asList(
            "Agnes", "Brian", "Harry", "Sally", "Tom", "Pamela",
            "Mark", "Wendy", "Marcia", "Graeme", "Pravztik",
            "Hadeep", "Florence", "Robert", "Zoe", "Frank");

    public String getNextAgentName() {
        return agents.get((int)( Math.random() *
                agents.size() ));
    }
}