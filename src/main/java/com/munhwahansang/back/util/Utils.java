package com.munhwahansang.back.util;

import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    public static Integer generateIdentifier() {
        return 100000 + random.nextInt(900000);
    }


}
