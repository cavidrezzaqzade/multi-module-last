package az.gov.mia.grp.listeners;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.util.*;

public class TrimListener {

    private final Map<Class<?>, Set<Field>> trimProperties = new HashMap<Class<?>, Set<Field>>();

    @PrePersist
    @PreUpdate
    public void repairAfterLoad(final Object entity) throws Exception {


        for (final Field fieldToTrim : getTrimProperties(entity.getClass())) {
            final String propertyValue = (String) fieldToTrim.get(entity);
            System.out.println(" = "+propertyValue);
            if (propertyValue != null)
                fieldToTrim.set(entity, propertyValue.trim());
        }
    }

    private Set<Field> getTrimProperties(Class<?> entityClass) throws Exception {
        if (Object.class.equals(entityClass))
            return Collections.emptySet();
        Set<Field> propertiesToTrim = trimProperties.get(entityClass);
        if (propertiesToTrim == null) {
            propertiesToTrim = new HashSet<Field>();
            for (final Field field : entityClass.getDeclaredFields()) {
                if (field.getType().equals(String.class)
                        && field.getAnnotation(Trim.class) != null) {
                    field.setAccessible(true);
                    propertiesToTrim.add(field);
                }
            }
            trimProperties.put(entityClass, propertiesToTrim);
        }
        return propertiesToTrim;
    }

}