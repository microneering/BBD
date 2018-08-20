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
public class EntityBean extends BBDDefaultRow<IBBDField> {

  private Long objectId;
  private String className;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
