/**
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
 * BBD/bbd/BBDParameterMetaData.java
 */
/**
 * @author james gamber
 *
 */
final class BBDParameterMetaData {
	
	private int type;
	
	private String name;
	
	private  int mode;

	BBDParameterMetaData(int mode, String name, int type) {
		this.mode = mode;
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the mode
	 */
	final int getMode() {
		return mode;
	}

	/**
	 * @return the name
	 */
	final String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	final int getType() {
		return type;
	}
}
