package com.mihail.studyshop.utils;

import java.util.UUID;

public class UuidUtils {
    public static boolean convertableToUuid(String maybeUuid){
        try {
            UUID.fromString(maybeUuid.trim());
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
