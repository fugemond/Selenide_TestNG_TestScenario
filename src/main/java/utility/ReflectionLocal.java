package utility;

import java.lang.reflect.Field;

public class ReflectionLocal {
    private ReflectionLocal() {

    }

    /**
     * Get page class with reflection
     */
    public static Object extractFieldValue(Field field, Object owner) {
        field.setAccessible(true);
        try {
            return field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}
