/*
 * TestDataModelRow.java
 *
 * Created on January 21, 2007, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * BBDDemoApplication/myApp.myDataModel/TestDataModelRow.java
 */
import bbd.BBDDefaultField;
import bbd.BBDDefaultRow;
import bbd.IBBDRow;

/**
 * 
 * @author james gamber
 * @param <E>
 * @param <BBBDDefaultField>
 */
@SuppressWarnings("serial")
public class TestDataModelRow<E extends IBBDRow<BBDDefaultField>> extends BBDDefaultRow<BBDDefaultField> {

	/** Creates a new instance of TestDataModelRow */
	public TestDataModelRow() {
	}

	public TestDataModelRow(final String name, final String address, final String city,
			final String state, final Integer zip) {

		add(new BBDDefaultField(name));
		add(new BBDDefaultField(address));
		add(new BBDDefaultField(city));
		add(new BBDDefaultField(state));
		add(new BBDDefaultField(zip));

	}
        
	/** public get methods
     * @return  */
	public String getName() {
		return getString(0);
	}

	public String getAddress() {
		return getString(1);
	}

	public String getCity() {
		return getString(2);
	}

	public String getState() {
		return getString(3);
	}

	public Integer getZip() {
		return getInteger(4);
	}

	@Override
	public boolean equals(final Object o) {
		
		if (!(o instanceof TestDataModelRow)) {
			return false;
		}
		
		final TestDataModelRow r = (TestDataModelRow)o;
		
		return getName().equals(r.getName()) &&
		                  getAddress().equals(r.getAddress()) &&
		                  getCity().equals(r.getCity()) &&
		                  getState().equals(r.getState()) &&
		                  getZip().equals(r.getZip());
		
	}

    @Override
    public int hashCode() {
        
        return super.hashCode();
    }
}
