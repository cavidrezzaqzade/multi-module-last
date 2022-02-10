package az.gov.mia.grp.util;

import java.lang.reflect.Field;

public class TrimUtil<T> {

    public void trim(T type) {
        Field[] fields = type.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                field.setAccessible(true);
                try {
                    if (field.get(type) != null) {
                        field.set(type, ((String) field.get(type)).trim());
                    }
                } catch (Exception e) {
                    System.out.println("error trim field="+field.getName());
                }
            }
        }
    }

}
