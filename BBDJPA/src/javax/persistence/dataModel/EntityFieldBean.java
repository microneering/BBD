/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence.dataModel;

/**
 *
 * @author James Gamber
 */
public class EntityFieldBean {
    
    private Long fieldId;
    private Long entityId;
    private Long classId;
    private String serializedValue;

    public

    Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue(String serializedValue) {
        this.serializedValue = serializedValue;
    }

  }
