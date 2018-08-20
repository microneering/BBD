/*
 * DataModelRow.java
 *
 * Created on January 20, 2007, 3:30 PM
 *
 * 
 */

package myApp.myDataModel;
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
 * BBDDemoApplication/myApp.myDataModel/HelloDataModelRow.java
 */
import bbd.BBDDefaultRow;
import bbd.IBBDField;

/**
 * Application data model layer is disconnect from internal details of database
 * schema.
 * 
 * @author james gamber
 */
@SuppressWarnings("serial")
public class HelloDataModelRow <E extends IBBDField> extends  BBDDefaultRow<E> {

	/** Creates a new instance of DataModelRow */
	public HelloDataModelRow() {
		super();
	}

	/**
	 * Map generic sql field to data model specific getter method.
	 * @return The value from the IBBDField
  */
	public String getHelloWordMessage() {
		final String s = (String) getBBDField(0).getValue();
		return s;
	}
}
