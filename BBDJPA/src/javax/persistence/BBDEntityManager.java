/*
 * BBDEntityManager.java
 *
 * Created on March 4, 2007, 3:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package javax.persistence;

import javax.persistence.dataModel.JavaClassBean;
import javax.persistence.dataModel.EntityBean;
import javax.persistence.dataModel.EntityFieldBean;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

import bbd.BBDAbstractFactory;
import bbd.BBDBeanArrayList;
import bbd.BBDValidator;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBeanAPI;
import bbd.IBBDBeanConnection;
import java.lang.reflect.Modifier;
import javax.persistence.reflection.CreateBean;

/**
 * 
 * @author James Gamber
 */
public class BBDEntityManager implements EntityManager {

    private static final Logger log = Logger.getLogger(BBDEntityManager.class.getName());

    private static final String persistDB = BBDJPAProperties.get(BBDJPAProperties.bbdJPPADB);
    private static final IBBDBeanAPI persistJavaClassAPI = BBDAbstractFactory.makeBBDBeanAPI(persistDB, "persistJavaClass", JavaClassBean.class);
    private static final IBBDBeanAPI persistEntityAPI = BBDAbstractFactory.makeBBDBeanAPI(persistDB, "persistEntity", EntityBean.class);
    private static final IBBDBeanAPI persistEntityFieldAPI = BBDAbstractFactory.makeBBDBeanAPI(persistDB, "persistEntityField", EntityFieldBean.class);

    static {

        // does persistence database exist
        try {
            if (!BBDValidator.isValidDatabase(persistDB)) {
                log.severe("JPA failure: JPA database (" + persistDB + ") does not exist!");
            }
        } catch (final SQLException e) {
            log.severe("JPA failure: JPA database (" + persistDB + ") does not exist!");
            e.printStackTrace();
        }

        // set persistance credential
        final IBBDAPIPrincipal bap = BBDAbstractFactory.makeBBDPrincipal(
                BBDJPAProperties.get(BBDJPAProperties.bbdJPAUser),
                BBDJPAProperties.get(BBDJPAProperties.bbdJPAPW));

        persistJavaClassAPI.setBbdPrincipal(bap);
        persistEntityAPI.setBbdPrincipal(bap);
        persistEntityFieldAPI.setBbdPrincipal(bap);
        
        CreateBean.setBbdPrincipal(
                BBDJPAProperties.get(BBDJPAProperties.bbdJPAUser),
                BBDJPAProperties.get(BBDJPAProperties.bbdJPAPW));

    // other things todo, load the JAXB xml peristence file.

    }
    private static final EntityManager managerInstance = new BBDEntityManager();

    /** Creates a new instance of BBDEntityManager */
    private BBDEntityManager() {
    }

    static public EntityManager getInstance() {
        return managerInstance;
    }

    @Override
    public void joinTransaction() {
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void clear() {
    }

    @Override
    public void close() {
    }

    @Override
    public Query createNativeQuery(final String sqlString, final String resultSetMapping) {
        return null;
    }

    @Override
    public void flush() {
    }

    @Override
    public EntityManager.FlushModeType getFlushMode() {
        return null;
    }

    @Override
    public EntityTransaction getTransaction() {
        return null;
    }

    @Override
    public void remove(final Object entity) {
    }

    @Override
    public void refresh(final Object entity) {
    }

    @Override
    public void persist(final Object entity) {
        final PersistenceProps entityPProps = getFields(entity);

        try {

            // use reflection to get the id of the entity
            Long entityId = null;
            Field entityIdField = null;
            
            for (final Field field : entityPProps.fields) {

                if (field.getAnnotation(javax.persistence.Id.class) != null) {
                    entityId = (Long) field.get(entity);
                    entityIdField = field;
                    break;
                }

            }

            // if this object was not persisted before, it has not entityId
            // so persist it

            if (entityId == null || entityId == 0) {
                // get the db key of the entity bean class
                final IBBDBeanConnection<EntityBean, BBDBeanArrayList<EntityBean>> entityConnetion = BBDAbstractFactory.makeBBDBeanConnection();
                final BBDBeanArrayList<EntityBean> entityList = entityConnetion.executeQuery(persistEntityAPI, entity.getClass().getName());
                entityId = entityList.get(0).getObjectId();
                entityIdField.set(entity, entityId);
            }

            // get db key of fields in the entity
            final IBBDBeanConnection<JavaClassBean, BBDBeanArrayList<JavaClassBean>> fieldTypeConnection = BBDAbstractFactory.makeBBDBeanConnection();
            final IBBDBeanConnection<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> fieldValueConnection = BBDAbstractFactory.makeBBDBeanConnection();
            int fieldPosition = 0;
            
            for (final Field field : entityPProps.fields) {

                // Do not persist transient fields
                if (Modifier.isTransient(field.getModifiers())) continue;
                
                // do noy persist the key ID field
                if (field == entityIdField) continue;

                // get the database foreign key for the java class of this field
                String className = field.get(entity).getClass().getName();
                Long classId = jclass.get(className);

                // not cached yet, so get it from database and cache it
                if (classId == null) {
                    BBDBeanArrayList<JavaClassBean> idList = fieldTypeConnection.executeQuery(persistJavaClassAPI, className);
                    jclass.put(className, idList.get(0).getJavaClassId());
                    classId = jclass.get(className);
                }

                /*
                // persist the values of the fields of entity into the db
                // put the value to the db using entityId, classId
                // fields are stored in a string so they can be retrieved in the order stored
                 * 
                 * This strategy assumes
                 *    many fields are strings
                 *    many fields have toString() values not much larger than their binary values
                 *          database size not importantly increased
                 *    object contruction from string values is trivail compared to network/database latentcy
                 */
                 
                 BBDBeanArrayList<EntityFieldBean> fieldIdList = fieldValueConnection.executeQuery(persistEntityFieldAPI, entityId, classId, fieldPosition++, field.get(entity).toString());
 
            }

        } catch (IllegalArgumentException ex) {
            log.log(Level.SEVERE, ex.toString(), ex);
        } catch (IllegalAccessException ex) {
            log.log(Level.SEVERE, ex.toString(), ex);
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, sqle.toString(), sqle);
        } finally {

        }

    }

    @Override
    public void lock(final Object entity, final EntityManager.LockModeType lockMode) {
    }

    @Override
    public boolean contains(final Object entity) {
        return true;
    }

    @Override
    public Query createNamedQuery(final String name) {
        return null;
    }

    @Override
    public Query createNativeQuery(final String sqlString) {
        return null;
    }

    @Override
    public Query createQuery(final String qlString) {
        return null;
    }

    @Override
    public void setFlushMode(final EntityManager.FlushModeType flushMode) {
    }

    @Override
    public <T> T merge(final T entity) {
        return null;
    }

    @Override
    public Query createNativeQuery(final String sqlString, final Class resultClass) {
        return null;
    }

    @Override
    public <T> T find(final Class<T> entityClass, final Object primaryKey) {
        return null;
    }

    @Override
    public <T> T getReference(final Class<T> entityClass, final Object primaryKey) {
        return null;
    }

    /**
     * Do reflection one time and cach it
     * 
     * @param obj
     */
    static private PersistenceProps getFields(final Object obj) {

        final Class clas = obj.getClass();
        PersistenceProps objPProps = pprops.get(clas);

        if (objPProps == null) {
            objPProps = new PersistenceProps();
            objPProps.fields = clas.getDeclaredFields();
            objPProps.name = clas.getName().replace('.', '_');

            // make all fields accessible
            for (final Field field : objPProps.fields) {
                field.setAccessible(true);
            }

            pprops.put(clas, objPProps);
        }

        return pprops.get(clas);

    }

    /**
     * Get the object's class fields
     * 
     * @author james gamber
     * 
     */
    static private class PersistenceProps {

        private Field[] fields;
        private String name;

        ArrayList getValues(final Object o) throws IllegalArgumentException,
                IllegalAccessException {
            final ArrayList<Object> fieldValues = new ArrayList<Object>();
            for (final Field fld : fields) {
                fieldValues.add(fld.get(o));
            }
            return fieldValues;
        }

        void setValues(final Object o, final Object[] values)
                throws IllegalArgumentException, IllegalAccessException {
            int index = 0;
            for (final Field fld : fields) {
                fld.set(o, values[index++]);
            }
        }
    }
    /**
     * The key is the object itself
     */
    static private final HashMap<Class, PersistenceProps> pprops = new HashMap<Class, PersistenceProps>(
            1000);
    /**
     * Map of java classes in use for persistance
     * Key is Class.getName()
     * Value is database sequence number
     */
    static private final HashMap<String, Long> jclass = new HashMap<String, Long>();

}
