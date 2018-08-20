/*
 * TestDataModelRow.java
 *
 * Created on January 21, 2007, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * BBDDemoApplication/myApp.myDataModel/TestDataModelRow.java
 */

/**
 * 
 * @author james gamber
 */
@SuppressWarnings("serial")
public class TestDataModelRow extends BBDDefaultRow<IBBDField> {

    /** Creates a new instance of TestDataModelRow */
    public TestDataModelRow() {
    }

    /**
     *
     * @param name
     * @param address
     * @param city
     * @param state
     * @param zip
     */
    public TestDataModelRow(final String name, final String address, final String city,
            final String state, final Integer zip) {

        add(new BBDDefaultField(name));
        add(new BBDDefaultField(address));
        add(new BBDDefaultField(city));
        add(new BBDDefaultField(state));
        add(new BBDDefaultField(zip));

    }

    /** public get methods*/
    /** 
     * @return Name from database row
     */
    public String getName() {
        return getString(0);
    }

    /**
     *
     * @return address from database row
     */
    public String getAddress() {
        return getString(1);
    }

    /**
     *
     * @return city from database row
     */
    public String getCity() {
        return getString(2);
    }

    /**
     *
     * @return state from database row
     */
    public String getState() {
        return getString(3);
    }

    /**
     *
     * @return zip code from database row
     */
    public Integer getZip() {
        return getInteger(4);
    }

    /** public set methods */
    /**
     * 
     * @param name to put in database row
     */
    public void setName(String name) {
        add(new BBDDefaultField(name));
    }

    /**
     *
     * @param address to put in database row
     */
    public void setAddress(String address) {
        add(new BBDDefaultField(address));
    }

    /**
     *
     * @param city to put in database row
     */
    public void setCity(String city) {
        add(new BBDDefaultField(city));
    }

    /**
     *
     * @param state to put in database row
     */
    public void setState(String state) {
        add(new BBDDefaultField(state));
    }

    /**
     *
     * @param zip to put in database row
     */
    public void setZip(String zip) {
        add(new BBDDefaultField(zip));
    }

    @Override
    public boolean equals(final Object o) {
        boolean equals; 

        if (!(o instanceof TestDataModelRow)) {
            return false;
        }

        final TestDataModelRow r = (TestDataModelRow) o;

        equals = getName().equals(r.getName()) &&
                getAddress().equals(r.getAddress()) &&
                getCity().equals(r.getCity()) &&
                getState().equals(r.getState()) &&
                getZip().equals(r.getZip());

        return equals;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = hash * 31 + getAddress().hashCode();
        hash = hash * 31 + getCity().hashCode();
        hash = hash * 31 + getName().hashCode();
        hash = hash * 31 + getState().hashCode();
        hash = hash * 31 + getZip().hashCode();
        return hash;
    }
}
