/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.persistence.dataModel;

import bbd.BBDDefaultRow;
import bbd.IBBDField;

/**
 *
 * @author James Gamber
 */
public class EntityFieldBean extends BBDDefaultRow<IBBDField> {


    public Long fieldId;
    public Long bbdjpaobjectId;
    public Long javaClassId;
    public String javaClassName;
    public String serializedValue;
    
    /**
     * @return the javaClassName
     */
    public String getJavaClassName() {
        return javaClassName;
    }
    /**
     * @param javaClassName the javaClassName to set
     */
    public void setJavaClassName( String javaClassName ) {
        this.javaClassName = javaClassName;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId( Long fieldId ) {
        this.fieldId = fieldId;
    }

    public Long getBbdjpaobjectId() {
        return bbdjpaobjectId;
    }

    public void setBbdjpaobjectId( Long bbdjpaobjectId ) {
        this.bbdjpaobjectId = bbdjpaobjectId;
    }

    public Long getJavaClassId() {
        return javaClassId;
    }

    public void setJavaClassId( Long javaClassId ) {
        this.javaClassId = javaClassId;
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue( String serializedValue ) {
        this.serializedValue = serializedValue;
    }

}
