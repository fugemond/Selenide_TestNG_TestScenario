package utility;

import core.exceptions.ExtractFieldValueException;

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
            throw new ExtractFieldValueException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}
