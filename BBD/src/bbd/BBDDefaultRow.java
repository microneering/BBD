/*
 * BBDDefaultRow.java
 *
 * Created on January 20, 2007, 11:53 AM
 *
 * Default implementation of row from stored procedure.
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
 * BBD/bbd/BBDDefaultRow.java
 */

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Default implementation of one row of data returned by the API.
 *
 * To create classes for specific tables, this class should be extended.
 *
 * @param <E> Any class extending BBDField type.
 *
 * @author james gamber
 */
@SuppressWarnings("serial")
public class BBDDefaultRow<E extends IBBDField> extends ArrayList<E> implements IBBDRow<E> {

    static final private Logger log = Logger.getLogger(BBDDefaultRow.class.getName());

    /**
     * Creates a new instance of BBDDefaultRow
     */
    public BBDDefaultRow() {
        super();
    }

    /**
     *
     * @param index
     *            for field requested, starts at 0.
     *
     * @return IBBDField at index.
     */
    @Override
    public E getBBDField(final int index) {

        return get(index);
    }

    @Override
    public String getString(final int index) {
        return (String) getBBDField(index).getValue();
    }

    @Override
    public Integer getInteger(final int index) {
        return (Integer) getBBDField(index).getValue();
    }

    /**
     * This prevents accidental calls to remove using collection and other
     * objects.
     * 
     * @param object Object you want to remove
     *
     * @return never returns, throws exception is used accidentally.
     *
     * @deprecated don't call remove with an Object type
     */
    @Override
    @Deprecated
    public boolean remove(final Object object) {
        log.severe("do not call remove with Object");
        throw new IllegalArgumentException("do not call remove with Object");

    }

    /**
     * Remove only IBBDField types.
     *
     * Prevents passing other type objects.
     *
     * @param object must be a IBBDField
     * @return true if object removed.
     */
    public boolean remove(final E object) {
        return super.remove(object);
    }

    /* (non-Javadoc)
     * @see bbd.IBBDRow#getDate(int)
     */
    @Override
    public Date getDate(final int index) {
        return (Date) getBBDField(index).getValue();
    }

    /* (non-Javadoc)
     * @see bbd.IBBDRow#getTime(int)
     */
    @Override
    public Time getTime(final int index) {
        return (Time) getBBDField(index).getValue();
    }

    /* (non-Javadoc)
     * @see bbd.IBBDRow#getTimestamp(int)
     */
    @Override
    public Timestamp getTimestamp(final int index) {
        return (Timestamp) getBBDField(index).getValue();
    }

    @Override
    public Blob getBlob(final int index) {
        return (Blob) getBBDField(index).getValue();
    }

    @Override
    public Boolean getBoolean(int index) {
        return (Boolean) getBBDField(index).getValue();
    }

    @Override
    public boolean equals(final Object o) {

        boolean equals = true;

        // must be not null
        if (o == null) return false;

        // must be same class
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }

        final BBDDefaultRow<BBDDefaultField> r = (BBDDefaultRow) o;

        // must have same number of fields
        if (this.size() != r.size()) return false;

        for (int i = 0; i < this.size(); i++) {
            equals = equals && equalsNullable(this.get(i).getValue(), r.get(i).getValue());
            if (!equals) {
                return equals;
            }
        }

        return equals;
    }

    @Override
    public int hashCode() {
        int hash = 3;

        for (int i = 0; i < this.size(); i++) {
            hash = hash * 31 + getHashCodeNullable(this.get(i).getValue());
        }

        return hash;
    }

    /**
     * Compare fields that can be null, not needed for NOT NULL fields.
     *
     * @param thisValue
     * @param otherValue
     *
     * @return True if fields are equal, false otherwise.
     */
    protected Boolean equalsNullable(final Object thisValue, final Object otherValue) {

        // if both part and parent are null, it doesnt change equals
        if (thisValue == null && otherValue == null) {
            return Boolean.TRUE;
        } else if (thisValue == null && otherValue != null) {
            return Boolean.FALSE;
        } else if (thisValue == null ) {
            return Boolean.FALSE;
        }
        return thisValue.equals(otherValue);
    }

    /**
     * Compute hash code for fields that can be null.  Directly calling the
     * hashCode method on a field that is null would make a null pointer
     * exception.
     *
     * @param thisField field that can be null.
     *
     * @return hashcode value of 0 for null field, else return normal hashcode.
     */
    protected int getHashCodeNullable(final Object thisField) {
        if (thisField == null) {
            return 0;
        }
        return thisField.hashCode();
    }
}
