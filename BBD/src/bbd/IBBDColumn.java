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
 * BBD/bbd/IBBDColumn.java
 */

/**
 * Interface to support database column names and types.
 * 
 * @author james gamber
 *
 */
public interface IBBDColumn {

	/**
	 * Get type of field: Integer, String, etc.
	 * 
	 * @param index of column in BBDRow.
	 * 
	 * @return field SQL type.
	 */
	public int getSqlType(int index);

	/**
	 * Get the column name. This would be the name used for a parameter in the
	 * stored procedure, or a name assigned by the application.
	 * 
	 * @param index of column in BBDRow.
	 * 
	 * @return String containing column name.
	 */
	public String getColumnName(int index);
        
        /**
         * Get column names.  This gets the list of all columns returned in in
         * result set meta data from the database query used to build an 
         * arrayList of result rows.
         * @return Array of names of columns that came from result set
         * meta data.
         */
         public String[] getColumnNames();
}
