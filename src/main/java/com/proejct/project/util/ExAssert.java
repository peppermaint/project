package com.proejct.project.util;

import org.springframework.lang.Nullable;
import sun.jvm.hotspot.utilities.Assert;

public class ExAssert extends Assert {

    public static void notNull(String message, Object... objects) {
        for(Object obj : objects) {
            if (obj == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
