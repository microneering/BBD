/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence.reflection;

import bbd.BBDBeanArrayList;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.BBDEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.dataModel.BBDEntityFieldBroker;
import javax.persistence.dataModel.EntityFieldBean;

/**
 *
 * @author James Gamber
 */
public class CreateBean {
    
    /** Getting the entity manager sets the user/pw */
    private static EntityManager em = BBDEntityManager.getInstance();
    private static String persistUser;
    private static String persistPassword;
    
    static Logger logger = Logger.getLogger(CreateBean.class.getName());

    static public <T> Class<T> getClass(final String entityClassName) {

        Class c = null;

        try {

            c = Class.forName(entityClassName);

        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    static public <T> T getInstance(final String entityClassName) {
        
        Class<T> c = getClass(entityClassName);
        
        T t = getInstance(c);
        
        return t;
    }
    
    static public <T> T getInstance(final Class<T> cl) {
        
        T t = null;
        
        try {

            t = cl.newInstance();

        } catch (InstantiationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return t;
    }
    
    static public Field getIDField(final String entityClassName) {

        return getIDField(getClass(entityClassName));
    }

    static public Field getIDField(final Object o) {

        return getIDField(o.getClass());
    }

    static public <T> Field getIDField(final Class<T> cl) {

        final Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {

            final Annotation[] annotations = field.getAnnotations();
            for (Annotation ano : annotations) {
                if (ano.annotationType().getName().equals("javax.persistence.Id")) {
                    return field;
                }
            }

        }

        return null;
    }
    
     static public String getIDFieldValue(final Object o) {
         
        try {

            Field field = getIDField(o.getClass());
            boolean accessiblity = field.isAccessible();
            field.setAccessible(true);
            String value = field.get(o).toString();
            field.setAccessible(accessiblity);

            return value;
        } catch (IllegalArgumentException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return null;
   }



    static public void setFieldValues(final Object instance) {

        EntityFieldBean efBean = new EntityFieldBean();
        efBean.setBbdjpaobjectId(Long.valueOf(getIDFieldValue(instance)));

        BBDEntityFieldBroker efBroker = new BBDEntityFieldBroker();
        efBroker.setPrincipal(persistUser, persistPassword);
        BBDBeanArrayList<EntityFieldBean> efList = efBroker.select(efBean);
        
        CreateBean.setFieldValues(efList, instance);
    }
    

    public static <T> void setFieldValues(BBDBeanArrayList<EntityFieldBean> efList, final T instance) {

        final Field[] fields = instance.getClass().getDeclaredFields();
        final Field idField = getIDField(instance);
        
        int index = 0;
        for (Field field : fields) {

            // id fields and transient fields are not persisted
            if (field.getName().equals(idField.getName())) continue;
            if (Modifier.isTransient(field.getModifiers())) continue;
            
            boolean accessiblity = field.isAccessible();
            try {

                String value = efList.get(index).getSerializedValue();
                field.setAccessible(true);

                if (field.getType().equals(String.class)) {
                    field.set(instance, value);
                } else if (field.getType().equals(Long.class)) {
                    field.set(instance, Long.valueOf(value));
                } else if (field.getType().equals(Integer.class)) {
                    field.set(instance, Integer.valueOf(value));
                } else if (field.getType().equals(Integer.TYPE)) {
                    field.set(instance, Integer.valueOf(value).intValue());
                }
            } catch (IllegalArgumentException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
                 
            field.setAccessible(accessiblity);
            index++;
            
        }
    }
    
     static public <T> void setFieldValue(final Field field, final T instance, final String value) {
        
        try {
            boolean accessiblity = field.isAccessible();
            field.setAccessible(true);
            if      (field.getType().equals(String.class))
                field.set(instance, value);
            else if (field.getType().equals(Long.class))
                field.set(instance, Long.valueOf(value));
            else if (field.getType().equals(Integer.class))
                field.set(instance, Integer.valueOf(value));
            else if (field.getType().equals(Integer.TYPE))
                field.set(instance, Integer.valueOf(value).intValue());
            field.setAccessible(accessiblity);
        }
        catch (IllegalArgumentException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }          
    }
    
    static public <T> void setIDFieldValue(final T instance, final String value) {
        
        setFieldValue(getIDField(instance), instance, value);
    }
    
    /**
     * 
     * @param userName
     * @param password
     */
    static public void setBbdPrincipal(final String userName, final String password) {
        
        persistUser = userName;
        persistPassword = password;
    }



}
