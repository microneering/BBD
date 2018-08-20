/*
 * IBBDField.java
 *
 * Created on January 20, 2007, 12:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 

 *
 */

package bbd;

import java.io.Serializable;

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
 * BBD/bbd/IBBDField.java
 */
/**
 * Interface for individual database values coming from or going to the database.
 * 
 * @author james gamber
 */
public interface IBBDField extends Serializable {

	/**
	 * Get the value of the field.
	 * 
	 * @return value of field.
	 */
	public Object getValue();
	
	
	/**
	 * Get the value of the field.as a string
	 * 
	 * @return value of field.as a string
	 */
    @Override
	public String toString();

}
