package az.gov.mia.grp.util;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.util.List;

public class TrimUtil<T> {

//    public void trim(T type) {
//        Field[] fields = type.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            if (field.getType() == String.class) {
//                field.setAccessible(true);
//                try {
//                    if (field.get(type) != null) {
//                        field.set(type, ((String) field.get(type)).trim());
//                    }
//                } catch (Exception e) {
//                    System.out.println("error trim field="+field.getName());
//                }
//            }
//        }
//    }

    public void trim(T type) {
        Field[] fields = type.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // private-i deyismek
            try {
                if (field.get(type) != null) { // deyeri null deyil
                    if (field.getType() == String.class) { // String
                        field.set(type, ((String) field.get(type)).trim());
                    } else if (field.getType() == List.class) { // List
                        List<Object> objects = (List) field.get(type);
                        for (Object object : objects) {
                            if (object.getClass().getTypeName().equals("java.lang.String")) {
                                int index = objects.indexOf(object);
                                if ((index > -1) & (object != null)) {
                                    objects.set(index, ((String) object).trim());
                                }
                            } else {
                                trim((T) object);
                            }
                        }
                    }else if(field.getType() instanceof Object){ //objects
                        if (!ClassUtils.isPrimitiveOrWrapper(field.getType())){ // primitiv deyilse
                            trim((T) field.get(type));
                        }else{
                            System.out.println("PrimitiveOrWrapper value="+field.get(type));
                        }
                    } else {
                        System.out.println("else class=" + field.getType().getTypeName());
                    }
                }
            } catch (Exception e) {
                System.out.println("error: TrimUtil field=" + field.getName());
            }
        }
    }


}
