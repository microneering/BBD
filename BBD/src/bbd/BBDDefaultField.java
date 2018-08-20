/*
 * BBDDefaultField.java
 *
 * Created on January 20, 2007, 12:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 

 *
 */
package bbd;
/*
 * Copyright 2007 microneering, Inc and James Gamber
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * BBD/bbd/BBDDefaultField.java
 */

/**
 * Default implementation class that encapsulated one field from one row from
 * the database.
 * 
 * These fields contain information generated dynamically in stored procedures.
 * 
 * @author james gamber
 */
public class BBDDefaultField implements IBBDField {

    /**
     * Value of field.
     */
    protected Object value;

    /**
     * Creates a new instance of BBDDefaultField
     */
    public BBDDefaultField() {
    }

    /**
     * Constructor to populate the object.
     *
     * @param value
     *            Value of the field, for example a name field might contain
     *            'Duke'.
     */
    public BBDDefaultField(final Object value) {
        this.value = value;
    }

    /**
     * Get value.
     *
     * @return Value stored in field.
     */
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     *
     * @param field
     * @return True if objects are equal.
     */
    @Override
    public boolean equals(Object field) {

        if (field == null) {
            return false;
        }
        if (!(field instanceof BBDDefaultField)) {
            return false;
        }
        if (getValue() == null && ((BBDDefaultField)field).getValue() == null) {
            return true;
        }

        return getValue().equals(((BBDDefaultField) field).getValue());
    }

    /**
     *
     * @return Hashcode value used to compare objects.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
}
