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
 */

/**
 * 
 * @author james gamber
 */
@SuppressWarnings("serial")
public final class TestDataBean {
    
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer zip;

	/** Creates a new instance of TestDataModelRow */
	public TestDataBean() {
	}

    /**
     *
     * @param name
     * @param address
     * @param city
     * @param state
     * @param zip
     */
    public TestDataBean(final String name, final String address, final String city,
			final String state, final Integer zip) {

		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);

	}

	

	@Override
	public boolean equals(final Object o) {
		boolean equals;
		
		if (!(o instanceof TestDataBean)) {
			return false;
		}
		
		final TestDataBean r = (TestDataBean)o;
		
		equals = getName().equals(r.getName()) &&
		                  getAddress().equals(r.getAddress()) &&
		                  getCity().equals(r.getCity()) &&
		                  getState().equals(r.getState()) &&
		                  getZip().equals(r.getZip());
		
		return equals;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 31 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 31 * hash + (this.state != null ? this.state.hashCode() : 0);
        hash = 31 * hash + (this.zip != null ? this.zip.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public Integer getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
