package com.immobel.utils;

import java.util.Optional;

public interface CommonUtils {

    public static boolean isNotBlank(String str) {
        return Optional.ofNullable(str).isPresent() && str.trim().length() > 0;
    }
    
    public static boolean isBlank(String str) {
        return !Optional.ofNullable(str).isPresent() || str.trim().length() == 0;
    }
}
