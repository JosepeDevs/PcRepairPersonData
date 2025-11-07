package com.josepedevs.testutil;

import java.util.Random;
import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

@UtilityClass
public class PreparedEasyRandom {

    private static EasyRandom create() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .collectionSizeRange(1, 1)
                .stringLengthRange(5, 5)
                .randomize(Integer.class, () -> Math.abs(new Random().nextInt(100)))
                .randomize(int.class, () -> Math.abs(new Random().nextInt(100)));

        return new EasyRandom(parameters);
    }

    public static final EasyRandom PREPARED_EASY_RANDOM = create();
}
