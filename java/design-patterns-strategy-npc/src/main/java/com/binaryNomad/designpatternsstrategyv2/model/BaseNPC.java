package com.binaryNomad.designpatternsstrategyv2.model;

import java.util.List;
import java.util.Random;

public class BaseNPC {

    protected List<String> greetPhrases;

    public String greetings() {
        Random random = new Random();
        int randomIndex = random.nextInt(greetPhrases.size());

        return greetPhrases.get(randomIndex);
    }
}
