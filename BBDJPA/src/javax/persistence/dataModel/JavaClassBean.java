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
public class JavaClassBean extends BBDDefaultRow<IBBDField> {
    
    private Long javaClassId;    
    private String  javaClassName;

    public Long getJavaClassId() {
        return javaClassId;
    }

    public void setJavaClassId(Long javaClassId) {
        this.javaClassId = javaClassId;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

   

}
