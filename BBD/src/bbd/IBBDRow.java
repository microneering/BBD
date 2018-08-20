/*
 * IBBDRow.java
 *
 * Created on January 20, 2007, 11:52 AM
 *
 * One row returned from result set.
 *
 * Convenience getters/setters should be added for all SQLTypes.
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
 * BBD/bbd/IBBDRow.java
 */
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface to one row of information in from the database.
 * 
 * @param <E>
 * @author james gamber
 */
public interface IBBDRow<E extends IBBDField> extends List<E>, Serializable {

	/**
	 * Get IBBDField value from 'index' position in row.
	 * 
	 * @param index
	 *            Index of field.
	 * @return DefaultBBDField
	 */
	public E getBBDField(int index);

	/**
	 * Get string value of IBBDField at index.
	 * 
	 * @param index
	 *            Index of field.
	 * @return String value from IBBDField.
	 */
	public String getString(int index);

	/**
	 * Get integer value from field at index.
	 * 
	 * @param index
	 *            Index of field.
	 * @return Integer value of IBBDField.
	 */
	public Integer getInteger(int index);
	
	
	/**
	 * Get timestamp value from field at index.
	 * 
	 * @param index
	 *            Index of field.
	 * @return Timestamp value of IBBDField.
	 */
	public Timestamp getTimestamp(int index);
	
	/**
	 * Get Date value from field at index.
	 * 
	 * @param index
	 *            Index of field.
	 * @return Date value of IBBDField.
	 */
	public Date getDate(int index);
	
	/**
	 * Get Time value from field at index.
	 * 
	 * @param index
	 *            Index of field.
	 * @return Time value of IBBDField.
	 */	
        public Time getTime(int index);

	/**
	 * Get Blob value of IBBDField at index.
	 *
	 * @param index
	 *            Index of field.
	 * @return Blob value from IBBDField.
	 */
	public Blob getBlob(int index);
	 
	/**
	 * Get Boolean value of IBBDField at index.
	 *
	 * @param index
	 *            Index of field.
	 * @return Boolean value from IBBDField.
	 */
	public Boolean getBoolean(int index);

}
