/*
 * BBDRefreshTimerTask.java
 *
 * Created on January 20, 2007, 2:40 PM
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
 * BBD/bbd/BBDRefreshTimerTask.java
 */
import java.util.TimerTask;

/**
 * Timer to periodically purge the database cached data.
 * 
 * @author james gamber
 */
class BBDRefreshTimerTask extends TimerTask {

	/**
	 * Creates a new instance of BBDRefreshTimerTask
	 * 
	 */
	BBDRefreshTimerTask() {
		super();
	}

	/**
	 * Run the refresh process to clear the cached database data.
	 * 
	 */
	@Override
	public void run() {

			BBDBeanConnection.clear();
			BBDValidator.clear();
	}
}
